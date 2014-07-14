package com.riviere.moomoney.exception;

import org.springframework.dao.DataAccessException;

/**
 * The main exception for the reporting manager application
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class MooMoneyException extends DataAccessException {
	
	private static final long serialVersionUID = 4944236164383295107L;
	private String errorCode;
	
	public MooMoneyException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public MooMoneyException(String errorCode, String message){
		super(message);
		this.errorCode = errorCode;
    }
	
	public MooMoneyException(String message){
		super(message);
    }
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
