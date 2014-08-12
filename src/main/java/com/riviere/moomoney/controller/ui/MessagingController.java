package com.riviere.moomoney.controller.ui;

import org.springframework.ui.ModelMap;

import com.riviere.moomoney.exception.MooMoneyException;

/**
 * This is the messaging controller interface to faciltate
 * handling of all messages shown to the user after form submit
 * or on form load.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
public interface MessagingController {

	/** Sucess Message Types */
	public final static String SUCCESS_MESSAGES = "successMessages";
	
	/** Warning Message Types */
	public final static String WARNING_MESSAGES = "warningMessages";
	
	/** Information Message Types */
	public final static String INFORMATION_MESSAGES = "informationMessages";
	
	/** Error Message Types */
	public final static String ERROR_MESSAGES = "errorMessages";
	
	/**
	 * Exception Message Types
	 */
	public final static String EXCEPTION_MESSAGES = "exceptionMessages";
	
	/**
	 * Adds a warming message.
	 * @param model The model to add the message to.
	 * @param message The message.
	 */
	public void addWarningMessage(ModelMap model, String message);
	
	/**
	 * Adds an information message.
	 * @param model The model to add the message to.
	 * @param message The message.
	 */
	public void addInformationMessage(ModelMap model, String message);
	
	/**
	 * Adds a success message.
	 * @param model The model to add the message to.
	 * @param message The message.
	 */
	public void addSuccessMessage(ModelMap model, String message);

	/**
	 * Adds an error message.
	 * @param model The model to add the message to.
	 * @param message The message.
	 */
	public void addErrorMessage(ModelMap model, String message);
	
	
	/**
	 * Adds the BusinessException message to the model map.
	 * If the BusinessException is wrapped exception, default technical error message 
	 * will be added to the mode map with message of the real cause as the detail exception.
	 * @param model The model to add the message to.
	 * @param e Business Exception
	 */
	public void addExceptionMessage(ModelMap model, MooMoneyException e);
}
