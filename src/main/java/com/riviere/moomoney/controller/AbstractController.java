package com.riviere.moomoney.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.ModelMap;

import com.riviere.moomoney.domain.ExceptionMessage;
import com.riviere.moomoney.exception.MooMoneyException;


/**
 * This is the Abstract Controller which all other concrete and abstract controllers
 * should extend off.
 *  
 * @author Richard Riviere
 * @date 24/04/2014
 */
public class AbstractController implements MessagingController {

	/**
	 * The logger for this class
	 */
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * A technical error message 
	 */
	@Value("#{messagesProperties['error.technical']}")
	public String technicalError = null;	
	
	/* (non-Javadoc)
	 * @see com.myer.reporting.controller.MessagingController#addErrorMessage(org.springframework.ui.ModelMap, java.lang.String)
	 */
	public void addErrorMessage(ModelMap model, String message) {
		addMessage(model, ERROR_MESSAGES, message);
	}


	/* (non-Javadoc)
	 * @see com.myer.reporting.controller.MessagingController#addExceptionMessage(org.springframework.ui.ModelMap, com.myer.reporting.exception.ReportingManagerException)
	 */
	public void addExceptionMessage(ModelMap model, MooMoneyException e) {
		List messages = getMessages(model, EXCEPTION_MESSAGES);
		String errorMessage = null;
		String detailedErrorMessage = null;
		Throwable cause = e.getCause();
		if (cause != null) {				
				detailedErrorMessage = getCauseMessage(cause);
				ExceptionMessage exceptionMessage = new ExceptionMessage(technicalError, detailedErrorMessage);
				messages.add(exceptionMessage);
				logger.error(technicalError, e);
		}
		else if (e.getMessage() != null && !e.getMessage().equalsIgnoreCase("")) {
			this.addErrorMessage(model, e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.debug("Handled business error. message: " + e.getMessage() , e);
			}
		}
	}


	/* (non-Javadoc)
	 * @see com.myer.reporting.controller.MessagingController#addInformationMessage(org.springframework.ui.ModelMap, java.lang.String)
	 */
	public void addInformationMessage(ModelMap model, String message) {
		addMessage(model, INFORMATION_MESSAGES, message);

	}


	/* (non-Javadoc)
	 * @see com.myer.reporting.controller.MessagingController#addSuccessMessage(org.springframework.ui.ModelMap, java.lang.String)
	 */
	public void addSuccessMessage(ModelMap model, String message) {
		addMessage(model, SUCCESS_MESSAGES, message);

	}

	
	/* (non-Javadoc)
	 * @see com.myer.reporting.controller.MessagingController#addWarningMessage(org.springframework.ui.ModelMap, java.lang.String)
	 */
	public void addWarningMessage(ModelMap model, String message) {
		addMessage(model, WARNING_MESSAGES, message);

	}
	
	/**
	 * Returns all messages currently on the model of a particular type.
	 * @param model The model.
	 * @param messagesType The message type.
	 * @return All messages currently on the model of a particular type.
	 */
	/**
	 * @param model
	 * @param messagesType
	 * @return
	 */
	private final List getMessages(ModelMap model, String messagesType ) {
		List messages = (List) model.get(messagesType);
		if (messages == null) {
			messages = new ArrayList();
			model.addAttribute(messagesType, messages);
		}
		return messages;
	}
	
	/**
	 * Adds a specific type of message.
	 * @param model The model for the form.
	 * @param messagesType The message type to be added (success, warning or information)
	 * @param message The message to be displayed.
	 */
	/**
	 * @param model
	 * @param messagesType
	 * @param message
	 */
	private final void addMessage(ModelMap model, String messagesType, String message) {
		List messages = getMessages(model, messagesType);
		messages.add(message);
	}
	
	/**
	 * @param th
	 * @return
	 */
	private String getCauseMessage(Throwable th) {
		String causeMessage = null;
		while (th != null) {
			if (th.getMessage() != null && !th.getMessage().equalsIgnoreCase("")){
				causeMessage = th.getMessage();
				break;
			}
			th = th.getCause();
		}
		
		return causeMessage;
	}
}
