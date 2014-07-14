// Common function to disable fields.
function disableFields(selector, clear) {
	var field = $(selector);
 	field.attr('disabled', 'disabled');
    field.addClass('disabled');
    removeErrors(field);
    if (clear === true) {
    	$(selector).val("");
    }
    
    if (field.hasClass("hasDatepicker")) {
    	field.next(".ui-datepicker-trigger").attr('disabled', 'disabled');
    	field.next(".ui-datepicker-trigger").addClass('disabled');
    }
}
    
// Common function to enable fields.
function enableFields(selector) {
  	var field = $(selector);
  	field.removeAttr('disabled');
   	field.removeClass('disabled');
   	if (field.hasClass("hasDatepicker")) {
		field.datepicker("option", "disabled", false);
		var datepickerBtn = field.next();
		if (datepickerBtn.hasClass("ui-datepicker-trigger")) {
			datepickerBtn.removeAttr('disabled');
			datepickerBtn.removeClass('disabled');
		}
    }
}

// Common function to readOnly fields.
function readOnlyFields(selector, clear) {
	var field = $(selector);
 	field.attr('readOnly', 'readOnly');
    field.addClass('disabled');
    removeErrors(field); 
    if (clear === true) {
    	$(selector).val("");
    }    
}

// Common function to clear fields.
function clearFields(selector) {	
	$(selector).val("");
	var field = $(selector);
	removeErrors(field); 
}

//Common function to remove errors
function removeErrors(element) {
	var closetDiv = $(element).closest('div');
	$(closetDiv).removeClass('ym-error'); 
	$(closetDiv).find('p.ym-message').remove();  
	var errFieldRowOffset = getErrorFieldRowOffset(element);	
	var currentForm = $(element).closest('form');
	toggleErrorElementToAllOffsetRows(element, currentForm, errFieldRowOffset, false);
}


// Common function for cancel
function cancel() {
	try {
		window.location.href = "cancel.htm?referer=" + window.location.href;
	} catch (e) { }
}

function back() {
	cancel();
}

// Common function for adding shortcut keys. We need to custom bind shortcut keys 
// to input fields separetly.
function addShortcutKey(shortcutKey, options) {
	var defaultType = 'keydown';
	var callback = options.callback;
	var type = options.type;
	if (options.button !== undefined && options.button !== null) {
		callback = function(event) { 
				if (options.button.is(":enabled")) {
					options.button.trigger('click');
				}
				return false;
			};
	}
	$(document).bind(type ? type : defaultType, shortcutKey, callback);
	if (options.disableInInput !== false) {
		var specialKeys = jQuery.hotkeys.specialKeys;
		var foundSpecialkey = false;
		for (var i in specialKeys) {
			if (specialKeys[i] === shortcutKey) {
				foundSpecialkey = true;
				break;
			}
		}
		
		// Special keys shortcuts (characters only i.e. return) are not 
		// bind to textareas.
		var elements = $(":input, :checkbox, :radio");
		if (foundSpecialkey) {
			elements = elements.not("textarea");
		}
   		elements.bind(type ? type : defaultType, shortcutKey, callback);
   	}
}

/**
 * Format a string based on the passed arguments
 * e.g "{0} plus {1) is equal to {2}".format(1,2,3) returns "1 plus 2 is equal to 3"
 */
String.prototype.format = function() {
	var args = arguments;      
	return this.replace(/\{(\d+)\}/g, function() {
		return args[arguments[1]];     
	}); 
};

/**
 * Gets URL query string attribute values
 */
function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}


/**
 * Expands all groups in a jqGrid table. Expanding all groups at once causes 
 * IE to freeze because JavaScript is single threaded where the execution 
 * stops as function calls queue up. To stop this from happening, we check 
 * if the code is busy executing at small time intervals (hard coded to 1ms) 
 * before calling the next waiting code to be executed.
 */
function expandAllGroups(gridId) {	
	var expandIcons = $(".ui-icon-circlesmall-plus");
	var index = 0, length = expandIcons.length, busy = false;
	
	if (length > 0) {
		$("#load_" + gridId).show();
		
		$("#" + gridId).jqGrid('setGridParam',{groupingView: { groupCollapse : false} });
		
		var processor = setInterval(function() {
				if (!busy) { 
					busy = true;
					
					$(expandIcons[index]).trigger("click");
					
					if (++index === length) { 
						clearInterval(processor);
						$("#load_" + gridId).hide();
					} 
					
					busy = false; 
				} 
			}, 1);
	}
}

/**
 * Collapses all groups in a jqGrid table. Collapsing all groups at once causes 
 * IE to freeze because JavaScript is single threaded where the execution 
 * stops as function calls queue up. To stop this from happening, we check 
 * if the code is busy executing at small time intervals (hard coded to 1ms) 
 * before calling the next waiting code to be executed.
 */
function collapseAllGroups(gridId) {		
	var collapseIcons = $(".ui-icon-circlesmall-minus");
	var index = 0, length = collapseIcons.length, busy = false;
	
	if (length > 0) {
		$("#load_" + gridId).show();	
		
		$("#" + gridId).jqGrid('setGridParam',{groupingView: { groupCollapse : true} });
		
		var processor = setInterval(function() {
				if (!busy) { 
					busy = true;
					
					$(collapseIcons[index]).trigger("click");
					
					if (++index === length) { 
						clearInterval(processor);
						$("#load_" + gridId).hide();
					} 
					
					busy = false; 
				} 
			}, 1);
	}
}

function isEmpty(value) {
    if (typeof value == 'undefined' || value === null || value === '') {
    	return true;
    }
    if (typeof value == 'number' && isNaN(value)) {
    	return true;
    }
    if (value instanceof Date && isNaN(Number(value))){
    	return true;
    }
    return false;
}

$.url = function(url) {
	return $('base').attr('href')+url.substr(1);
}

/**
 * Find the row offset for the element in error.
 * 
 * @param element the element in error
 * @returns {Number} the row offset of the element in error
 */
function getErrorFieldRowOffset(element) {	
	// the grandparent should be the divs with the ids 
	// equal to col1, col2 etc. etc.    	    		        
	var $grandParentEl = $(element).parent().parent();
	
	// for each column div loop through for the purpose of finding the 
	// element in error. On finding the element in error get a row offset	
	var errColOffset = 0;
	$grandParentEl.children(".ym-fbox").each(function() {
		++errColOffset;
		var breakLoop = false;
    	$(this).children().each(function() {       
    		var childId = $(this).attr("id");    		
    		var elementId = $(element).attr("id"); 
    		if (childId == elementId){
    			breakLoop = true;
    			return( false );
    		}
    	});  
		if (breakLoop == true){
			return( false );
		}        		    	
	});	
	return errColOffset;
}

/**
 * For all rows matching the row offset toggle an error message. This is
 * used to ensure the form rows remains aligned correctly after a  
 * validation error is shown to the user on the screen.
 * 
 * @param element the element in error
 * @param form the form
 * @param errFieldRowOffset the row offset of the element in error
 * @param onError true if called onError, false if called when the error is removed
 */
function toggleErrorElementToAllOffsetRows(element, form, errFieldRowOffset, onError) {		
	var grandParentId = $(element).parent().parent().attr("id");
	$(form).children().children().each(function() {
		var colId = $(this).attr("id");
		if (grandParentId != colId){
			var scanColOffset = 0;    						
	    	$(this).children(".ym-fbox").each(function() {    
	    		++scanColOffset;
	    		if (scanColOffset == errFieldRowOffset){
	    			if (onError){
	    				var $messageEl = $(this).find("p.ym-message");
	    				if (!$messageEl.length) {
	    					$(this).append("<p class='ym-message'>&nbsp;</p>");
	    				} else {
	    					$messageEl.replaceWith("<p class='ym-message'>&nbsp;</p>");
	    				}
	    			}else {
	    				$("p.ym-message").remove();	    				
	    			}
	    		}
	    	});     						
		}
	});	
}
