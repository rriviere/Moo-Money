package com.riviere.moomoney.util;

import org.apache.commons.lang.StringUtils;

/**
 * A set of request utilities 
 * 
 * @author Richard Riviere
 * @date 08/05/2014
 */
public class RequestUtils {

	/**
	 * Compares two URLs but ignores the parameters.
	 * @param requestURL1 
	 * @param requestURL2
	 * @return True if the URLs are the same irrespctive of parameters.
	 */
	public static boolean equalsIgnoreParameters(String requestURL1, String requestURL2) {
		String url1 = stripParameters(requestURL1);
		String url2 = stripParameters(requestURL2);
		return StringUtils.equalsIgnoreCase(url1, url2);
	}
	
	/**
	 * Strips parameters from a URL
	 * @param requestURL
	 * @return
	 */
	public static String stripParameters(String requestURL) {
		return StringUtils.substringBefore(requestURL, "?");
	}
}
