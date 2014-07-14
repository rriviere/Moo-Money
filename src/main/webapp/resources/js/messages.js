function addError(error) {
	var message = "<div class='box error'>" + error + "</div>";
	$("#messageBox").empty();
	$("#messageBox").append(message);
}

function appendError(error)  {
	var message = "<div class='box error'>" + error + "</div>";
	$("#messageBox").append(message);
}

function clearMessages() {
	$("#messageBox").empty();
	// IE6 fix: Require something inside the DIV to ensure zero height when it contains no content.
	$("#messageBox").append("<!-- Message Box -->");
}

function resolveServerErrors(data, status, xhr) {

	if (data.errorMessages != undefined && data.errorMessages.length > 0) {
		for (var j=0; j < data.errorMessages.length; j++) {
			addError(data.errorMessages[j]);
		}
	}
	else if (data.exceptionMessages != undefined && data.exceptionMessages.length > 0){
		addError(data.exceptionMessages[0].message);
	}
}

function resolveServerErrorsAndPreserveMsgs(data, status, xhr) {

	if (data.errorMessages != undefined && data.errorMessages.length > 0) {
		for (var j=0; j < data.errorMessages.length; j++) {
			appendError(data.errorMessages[j]);
		}
	}
	else if (data.exceptionMessages != undefined && data.exceptionMessages.length > 0){
		appendError(data.exceptionMessages[0].message);
	}
}