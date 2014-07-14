jQuery.validator.addMethod(
	"repManDate",
	function(value, element){
		// dd/MM/yyyy format with leap years integrated. Valid years : from 1900 to 9999 
		return this.optional(element)||/^(((0[1-9]|[12]\d|3[01])\/(0[13578]|1[02])\/((19|[2-9]\d)\d{2}))|((0[1-9]|[12]\d|30)\/(0[13456789]|1[012])\/((19|[2-9]\d)\d{2}))|((0[1-9]|1\d|2[0-8])\/02\/((19|[2-9]\d)\d{2}))|(29\/02\/((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))))$/i.test(value);
	},	
	"Please enter a valid date"
),
jQuery.validator.messages.required = "Field is required.";       
jQuery.validator.messages.range = "Value range is {0} to {1}.";
$().ready(function() {
    $("form").each(function() {
    	var currentForm = $(this);
        $(this).validate({
			onblur: false,
			onchange: false,
			onkeyup: false,
			submitHandler: function(form) {
				$(form).removeClass('dirty');
	        	$('button').attr('disabled','disabled');
	        	$('button').addClass('disabled');
	           	form.submit();
	    	},
			errorClass:'ym-message',
			errorElement: 'p',
		    errorPlacement: function(error, element) {
		    	error.insertAfter(element);
		    },
	        highlight: function (element, errorClass, validClass) { 
	        	$(element).closest("div").addClass("ym-error");
		    	var errFieldRowOffset = getErrorFieldRowOffset(element);		    	
		    	toggleErrorElementToAllOffsetRows(element, currentForm, errFieldRowOffset, true);
	        },      
	        unhighlight: function (element, errorClass, validClass) { 
	        	$(element).closest("div").removeClass("ym-error"); 
		    	var errFieldRowOffset = getErrorFieldRowOffset(element);		    	
		    	toggleErrorElementToAllOffsetRows(element, currentForm, errFieldRowOffset, false);	        	
	        },	    
			ignore: ".ignore"
        });     	
    });
});

