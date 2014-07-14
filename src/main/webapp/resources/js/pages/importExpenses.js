$().ready(function() {
    $("#fileupload").fileupload({
        dataType: "json",
        done: function (e, data) {
        	$("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
            	parent.$("#uploaded-files").append(
            		$("<tr/>")
                	.append($("<td/>").text(file.fileId))
                	.append($("<td/>").text(file.fileName))
                	.append($("<td/>").text(file.fileSize))
                	.append($("<td/>").text(file.fileType))
                	.append($("<td/>").html("<a href='controller/get/"+index+".htm'>Click</a>"))
                	)
            }); 
        },
        progressall: function (e, data) {
	        var progress = parseInt(data.loaded / data.total * 100, 10);
	        $("#progress .bar").css(
	            "width",
	            progress + "%"
	        );
   		},
   		
		dropZone: $("#dropzone")
    });	  
});
