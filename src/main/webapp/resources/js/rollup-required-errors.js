jQuery.validator.messages.required = "";
$().ready(function() {
	$("form").validate({
		invalidHandler : function(e, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				var message = errors + ' field(s) are invalid. ';
				message += 'Mandatory fields are highlighted. All other errors are displayed underneath relevant field(s). Please fix errors before continuing.';
				addError(message);
			} else {
				clearMessages();
			}
		},
		onkeyup : false
	});
	
	function addError(error) {
		var message = "<div class='error'>" + error + "</div>";
		$("#messageBox").empty();
		$("#messageBox").append(message);
	};

	function clearMessages() {
		$("#messageBox").empty();
	}
});

