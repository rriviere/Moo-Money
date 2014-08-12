
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
