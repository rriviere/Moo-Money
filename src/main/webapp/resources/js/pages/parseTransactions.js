
$().ready(function() {
	
	$('#totopscroller').totopscroller({
		link: false
	});
	
	// load transaction categories to be used with autocomplete
	$.get("service/transactionCategories.json", {}, function(tdata){
				var eetags = new Bloodhound({
					datumTokenizer: Bloodhound.tokenizers.obj.whitespace("tranCategoryDesc"),
					queryTokenizer: Bloodhound.tokenizers.whitespace,
					local: tdata
				});
				eetags.initialize();
			
				var elt = $("input.eetag");
				elt.tagsinput({
					maxTags: 1,
					itemValue: "tranCategoryCode",
					itemText: "tranCategoryDesc",
					typeaheadjs: {
						name: "tranCategoryCode",
						displayKey: "tranCategoryDesc",
						source: eetags.ttAdapter()
					},
					tagClass: function(item) {
						switch (item.buttonType) {
						  case "primary" : return "label label-primary";
						  case "important" : return "label label-danger label-important";
						  case "success" : return "label label-success";
						  case "default" : return "label label-default";
						  case "warning" : return "label label-warning";
						}
					},
				});	
			});

	$('.btn-file :file').on('fileselect', function(event, numFiles, label) {
        var input = $(this).parents('.input-group').find(':text'),
            log = numFiles > 1 ? numFiles + ' files selected' : label;
        
        if( input.length ) {
            input.val(log);
        } else {
            if( log ) alert(log);
        }        
    });
    	
});

$().on('change', '.btn-file :file', function() {
	var input = $(this),
    	numFiles = input.get(0).files ? input.get(0).files.length : 1,
    	label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    	input.trigger('fileselect', [numFiles, label]);
});

$(document).ready(function(){
	
	// for each row search for a transaction category by passing the transaction description
	$(".eetag").each(function(){
	    var currentItem = $(this);
	    var desc = $(this).attr('data-desc');
	    $.ajax({ 
	        url: 'service/transactionCategoryByKeyword.json',           
	        type: 'GET',
	        async: false,
	        contentType: 'application/json',
	        data: {'phrase': desc},     
	        success: function(data) {
	            $(currentItem).tagsinput('add', data);
	        }	        
	    });     
	});
	
	$("#save").click(function(){
		var count = {};
		$(".transaction-row").each(function(){
			var tranCategoryCode = $.trim($(this).find("td input.eetag").val());
			//alert("tranCategoryCode: " + tranCategoryCode);
			count[tranCategoryCode] = count[tranCategoryCode] + 1 || 1;
			
		});
		
		var message = "<div>Here is a summary of what you are about to save:<br></br></div>";
		message+="<div>";
		message+="<table width='400'><thead style='text-decoration: underline;'><tr><th>Transaction Type</th><th>Number of Transactions</th></tr></thead>";
		message+="<tbody>";

		$.each(count, function(key, valueObj){
			message += "<tr><td>" + key + "</td><td>" + valueObj + "</td></tr>";
		});		

		message+="</tbody></table>";
		message+="</div>";
		
        BootstrapDialog.show({
        	type: BootstrapDialog.TYPE_WARNING,
        	title: 'Transaction Summary',
            message: message,
            buttons: [{
            	id: 'btn-1',
                icon: 'glyphicon glyphicon-ok',
                label: 'Proceed',
                cssClass: 'btn-primary',
                autospin: true,
                action: function(dialogRef){
                    dialogRef.enableButtons(false);
                    dialogRef.setClosable(false);
                    setTimeout(function(){
                    	saveTransactions();
                    	
                    	dialogRef.setType(BootstrapDialog.TYPE_SUCCESS);
                        dialogRef.setTitle('Save successful');
                        var saveCnt = $('.transaction-row').length;
                        dialogRef.getModalBody().html("<span style='font-weight:bold'>" + saveCnt + "</span> transactions successfully saved.");
                        dialogRef.setClosable(true);
                        // modify left button
                        var $btnLeft = dialogRef.getButton('btn-1');
                        $btnLeft.stopSpin(); 
                        $btnLeft.enable();
                        $btnLeft.html("Continue to edit saved transactions");
                        $btnLeft.click(function(event){
                        	dialogRef.close();
                        	$("#save").attr('disabled','disabled');
                        	$(".page-header").after("<mark>Expenses are successfully saved.</mark>");
                        });
                        // modify right button                                                
                        var $btnRight = dialogRef.getButton('btn-2');
                        $btnRight.enable();
                        $btnRight.html("Home");   
                        $btnRight.click(function(event){
                        	window.location.href = "home.htm";
                        });                        
                        
                    }, 4000);
                }
            }, {
            	id: 'btn-2',
                label: 'Review',
                icon: 'glyphicon glyphicon-remove',
                action: function(dialogRef){
                    dialogRef.close();
                }
            }]
        });
	});
});

function saveTransactions() {
	var params =[];
	// loop through each transaction row and save it
	$(".transaction-row").each(function(){
		// set the credit field
		var credit = $.trim($($(this).find("td").get(3)).text());
		if(credit == ""){
			credit= "0.0";
		}
		// set other fields
		params =[];		
		var transaction = {
				"tranDate": $.trim($($(this).find("td").get(0)).text()),
				"tranDescription": $.trim($($(this).find("td").get(1)).text()),
				"debit": $.trim($($(this).find("td").get(2)).text()),
				"credit": credit,
				"tranCategoryCode": $.trim($(this).find("td input.eetag").val())};
		params.push(transaction);
		
		// do the save
		var currentRow = $(this);
		$.ajax({
		    url: 'expenseList.htm',
		    type: 'POST',
		    async: true,
		    contentType: 'application/json',
		    data: JSON.stringify(params), //stringify is important   
		    success: function(data){
				$($(currentRow).find("td").get(0)).attr("data-id", data);
				$(currentRow).find("fieldset").removeAttr("disabled");
				$(currentRow).find("td input[type='file']").fileupload({
			        dataType: "json",
			        done: function (e, fdata) {
			        	$.post("saveReceipt.htm", {"fileId":fdata.result[0].fileId,"tranId":$($(currentRow).find("td").get(0)).attr("data-id") }, function(rdata){ 
			        		 $(currentRow).find(".input-group .btn-file").after("<a  style='width:85px;' href='controller/get/"+fdata.result[0].fileId+".htm' class='btn btn-success'>View</a>");
			        		 $(currentRow).find(".input-group .btn-file").remove();
			        		 $(currentRow).find(".input-group .form-control").val(fdata.result[0].fileName);
			        		 $(currentRow).find("td #p-"+$(currentRow).attr("data-count")).remove();
			        		 $(currentRow).find("td .help-block").text("Click here to see Receipt");
			        	 });
			        },
			        progressall: function (e, data) {
				        var progress = parseInt(data.loaded / data.total * 100, 10);
				        $(currentRow).find("td #p-"+$(currentRow).attr("data-count")+" .bar").css(
				            "width",
				            progress + "%"
				        );
			   		},
			    });	  
		    }
		});
	});
}
