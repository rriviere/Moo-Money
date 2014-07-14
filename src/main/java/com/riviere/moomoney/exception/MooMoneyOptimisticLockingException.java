package com.riviere.moomoney.exception;

/**
 * An optimistic locking exception for the reporting manager application.
 * 
 * @author Richard Riviere
 * @date 01/04/2014
 */
public class MooMoneyOptimisticLockingException 
	extends MooMoneyException {
	
	private static final long serialVersionUID = 4317007192863983310L;	

	public MooMoneyOptimisticLockingException(String message, Throwable cause) {
		super(message, cause);
	}	
	
	public MooMoneyOptimisticLockingException(String errorCode, String message){
		super(errorCode, message);
    }
	
	public MooMoneyOptimisticLockingException(String message){
		super(message);
    }
}
