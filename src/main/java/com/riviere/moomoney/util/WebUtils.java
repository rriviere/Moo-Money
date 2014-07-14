package com.riviere.moomoney.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.riviere.moomoney.constants.ParamConstants;

/**
 * Utilities relating to web technologies
 * 
 * @author Richard Riviere
 * @date 17/02/2014
 */
public class WebUtils {
	
	private static final Log logger = 
			LogFactory.getLog(WebUtils.class);

	/**
	 * Delete the cookie with the specified name
	 * 
	 * @param cookieName the name of the cookie to deltee
	 * @param servletRequest the serverlet request
	 * @param servletResponse the servlet respose
	 */
	public static void eraseCookie (	
			final String cookieName,
			final HttpServletRequest servletRequest, 
			final HttpServletResponse servletResponse) {
		  
		Cookie[] allCookies = servletRequest.getCookies();
		for (int i = 0; i < allCookies.length; i++) {
			Cookie candidateCookie = allCookies[i];		
			if (candidateCookie.getName().equalsIgnoreCase(cookieName)){	
				Cookie cookieToDelete = allCookies[i];
				cookieToDelete.setValue("");
				cookieToDelete.setMaxAge(0);
				cookieToDelete.setVersion(0);
								
				// set cookie path
				String pathOfCookieToDelete = "";				
				if (candidateCookie.getPath()!= null){
					pathOfCookieToDelete = servletRequest.getContextPath();	
				}
				cookieToDelete.setPath(pathOfCookieToDelete + ParamConstants.COOKIE_DEFAULT_PATH);
				logger.debug(
						"deleted cookie:" + cookieToDelete.getName() 
						+ " cookie path :" + cookieToDelete.getPath()
						+ " cookie age :" + cookieToDelete.getMaxAge()
						+ " cookie value :" + cookieToDelete.getValue()
						+ " cookie version :" + cookieToDelete.getVersion()
						);	
				
				servletResponse.setContentType("text/html");
				cookieToDelete.setComment("expiring cookie at " + System.currentTimeMillis());
				servletResponse.addCookie(cookieToDelete);
		    }
		}
	}
	
	/**
	 * Get the value of a cookie
	 * 
	 * @param cookieName the name of the cookie to get the value of 
	 * @param servletRequest the servlet request
	 * @return the string value of the cookie
	 */
	public static String getCookieValue(
			String cookieName,
			final HttpServletRequest servletRequest) {
		
		Cookie[] cookies = servletRequest.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			String name = cookies[i].getName();
			String value = cookies[i].getValue();
			if (cookieName.equals(name)) {
				return value;
			}
		}
		return null;
	}	
	
}
