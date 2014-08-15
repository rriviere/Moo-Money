
$().ready(function() {
	
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
						  case "primary"   : return "label label-primary";
						  case "important"  : return "label label-danger label-important";
						  case "success": return "label label-success";
						  case "default"   : return "label label-default";
						  case "warning"     : return "label label-warning";
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
$(".eetag").each(function(){

		var currentItem = $(this);
		var desc = $(this).attr('data-desc');
		$.get('service/transactionCategoryByKeyword.json',{'phrase': desc},function(data){
			$(currentItem).tagsinput('add', data);
		});

});

$("#save").click(function(){
	$(this).remove();
	var params =[];
	$(".transaction-row").each(function(){
		var currentRow = $(this);
		var credit = $.trim($($(this).find("td").get(3)).text());
		if(credit == ""){
			credit= "0.0";
		}
		params =[];
		var transaction = {"tranDate": $.trim($($(this).find("td").get(0)).text()) , "tranDescription": $.trim($($(this).find("td").get(1)).text()), "debit": $.trim($($(this).find("td").get(2)).text()), "credit": credit, "tranCategoryCode": $.trim($(this).find("td input.eetag").val())};
		params.push(transaction);
		
		$.ajax({
		    url: 'expenseList.htm'
		,   type: 'POST'
		,	async: true
		,   contentType: 'application/json'
		,   data: JSON.stringify(params) //stringify is important
		,   success: function(data){
				$($(currentRow).find("td").get(0)).attr("data-id", data);
				$(currentRow).find("td input[type='file']").removeAttr("disabled");
				$(currentRow).find("td input[type='file']").fileupload({
			        dataType: "json",
			        done: function (e, fdata) {
			        	$.post('saveReceipt.htm', {'fileId':fdata.result[0].fileId, 'tranId':$($(currentRow).find("td").get(0)).attr("data-id") }, function(rdata){ 
			        		 $(currentRow).find(".input-group .btn-file").after("<a  style='width:85px;' href='controller/get/"+fdata.result[0].fileId+".htm' class='btn btn-danger'>View</a>");
			        		 $(currentRow).find(".input-group .btn-file").remove();
			        		 $(currentRow).find(".input-group .form-control").val(fdata.result[0].fileName);
			        		 $(currentRow).find("td #p-"+$(currentRow).attr("data-count")).remove();
			        		 $(currentRow).find("td .help-block").text("Click here to see Receipt");
			        	 });

			           /* $.each(fdata.result, function (index, file) {
			            	 
			            });*/ 
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
	$(".page-header").after("<mark>Expenses are successfully saved.</mark>");
	
});
});

	
/*
$('.eetag').on('itemAdded', function(event) {
	  alert(event.item.val());
	  // event.item: contains the item
	  //alert(event.item.tranCategoryCode);
});
$(".eetag").each(function(){
	$(this).tagsinput('add', 'Phone');
})
 $("#tblTransactions > tbody  > tr").each(function(){
	var elt = $(this).find("td.colCategory > input").val();
	var tranDesc = $(this).find("td.colTranDesc").text(); 
    alert(elt);
});	*/
