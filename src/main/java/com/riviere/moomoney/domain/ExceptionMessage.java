package com.riviere.moomoney.domain;

public class ExceptionMessage extends AbstractDomainObject {
	
	private static final long serialVersionUID = -426272537220449796L;
	private String message;
	private String detailedMessage;

	public ExceptionMessage(String message, String detailedMessage) {
		super();
		this.message = message;
		this.detailedMessage = detailedMessage;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the detailedMessage
	 */
	public String getDetailedMessage() {
		return detailedMessage;
	}

	/**
	 * @param detailedMessage the detailedMessage to set
	 */
	public void setDetailedMessage(String detailedMessage) {
		this.detailedMessage = detailedMessage;
	}
	
	public String toString(){
		StringBuffer object = new StringBuffer();
		object.append("ExceptionMessage[ ");
		object.append("message:" + message);
		object.append("detailedMessage:" + detailedMessage);
		object.append("]");
		return object.toString();
	}
}
