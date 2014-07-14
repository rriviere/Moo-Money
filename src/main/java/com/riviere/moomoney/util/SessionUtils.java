package com.riviere.moomoney.util;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A set of session utilities 
 * 
 * @author Richard Riviere
 * @date 08/05/2014
 */
public class SessionUtils {
	
	/**
	 * The logger.
	 */
	private static final Log logger = LogFactory.getLog(SessionUtils.class);
	
	/**
	 * Constant to define Session Key.
	 */
	private static final String REP_MAN_SESSION = "REP_MAN_SESSION";
	
	/**
	 * Prefix for the session item keys
	 */
	private static final String REP_MAN_SESSION_ITEM = "REP_MAN_SESSION_ITEM";
	
	public static final String SELECTED_DELIVERY_INFO = "SELECTED_DELIVERY_INFO";
	
	/**
	 * Sets the object into HTTP session
	 * @param session HttpSession object
	 * @param key Key that define the object
	 * @param value Value Object to be store
	 */
	public static void set(HttpSession session, String key, Object value) {
		
		Map sessionMap = getSessionMap(session);
		if (sessionMap == null)  {
			if (logger.isDebugEnabled()) {
				logger.debug("No REP_MAN_SESSION object in HTTP session, creating new");
			}
			sessionMap = new Hashtable();
		}
		sessionMap.put(getItemKey(key), value);
		setSessionMap(session, sessionMap);		
				
	}
	
	/**
	 * Returns the Object for the given key stored in the HttpSession
	 * @param session HttpSession object
	 * @param key Key that defined the object
	 * @return Object stored in the HttpSession
	 */
	public static Object get(HttpSession session, String key) {
		Map sessionMap = getSessionMap(session);
		if (sessionMap == null)  {
			if (logger.isDebugEnabled()) {
				logger.debug("No REP_MAN_SESSION object in HTTP session. Cannot find a value to key : " + key);
			}
			return null;
		}
		return sessionMap.get(getItemKey(key));
	}
	
	/**
	 * Remove the specified element from session application map
	 * @param session HttpSession
	 * @param key Item key
	 */
	public static void remove(HttpSession session, String key) {
		Map sessionMap = getSessionMap(session);
		if (sessionMap != null)  {
			sessionMap.remove(getItemKey(key));
			setSessionMap(session, sessionMap);
		}
	}
	
	/**
	 * Returns unique session key for the given session with application specific prefix
	 * @param session HttpSession
	 * @return Unique key comprising session id and ht eapplication specific constant
	 */
	private static String getSessionKey (HttpSession session) {
		return REP_MAN_SESSION + session.getId();
	}
	
	/**
	 * Returns an application specific unique key for the give key
	 * @param key
	 * @return unique key comprising applicattion specific prefix
	 */
	private static String getItemKey(String key) {
		return REP_MAN_SESSION_ITEM + "_" + key;
	}
	
	/**
	 * Returne the session map from HttpSession
	 * @param session HttpSession
	 * @return session map
	 */
	private static Map getSessionMap(HttpSession session) {
		return (Map)session.getAttribute(getSessionKey(session));
	}
	
	/**
	 * Sets the session map into HttpSession
	 * @param session HttpSession
	 * @param sessionMap session map
	 */
	private static void setSessionMap(HttpSession session, Map sessionMap) {
		session.setAttribute(getSessionKey(session), sessionMap);
	}
	
	/**
	 * Clears the session from HttpSession
	 * @param session
	 */
	public static void clearSession(HttpSession session) {
		session.removeAttribute(getSessionKey(session));
		
	}
	
	/**
	 * Validate the key is a defined constant of the Session Util class
	 * @param key String containing the key
	 * @return returns true if the key is defined, otherwise false
	 */
	public static boolean isValidKey(String key) {
		try {
			SessionUtils.class.getDeclaredField(key);
		} catch (SecurityException e) {
			logger.error("Security Exception in reading the defined keys",e);
			throw e;
		} catch (NoSuchFieldException e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Key not found, Invalid Key :" + key);
			}
			return false;
		}
		return true;
	}

}
