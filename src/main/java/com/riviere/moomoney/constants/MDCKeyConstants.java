package com.riviere.moomoney.constants;

/**
 *  Interface to store valid MDC Keys for Log4j
 *  
 * @author Richard Riviere
 */
public class MDCKeyConstants {
	/**
	 * Key for user id of the user who initiated the request
	 */
	public static final String KEY_USER_ID = "userId";
	
	/**
	 * Key for session id of the user who initiated the request
	 */
	public static final String KEY_SESSION_ID = "sessionId";
	
	/**
	 * Key for remote address of the user who initiated the request
	 */
	public static final String KEY_REMOTE_ADDR = "remoteAddr";
	
	/**
	 * MDC Value for the default user id if not known
	 */
	public static final String DEFAULT_USER_ID = "UNKNOWN";
	
	/**
	 * MDC Value for the concurrent user count
	 */
	public static final String CONCURRENT_USER_COUNT = "concurrentUserCnt";	
}
