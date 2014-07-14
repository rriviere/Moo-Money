package com.riviere.moomoney.constants;

/**
 * This class holds constants used for to request parameters
 * 
 * @author Richard Riviere
 * @date 05/02/2014
 */
public class ParamConstants {
	
	// PARAMETERS
	public static final String PARAM_PREFIX = "?";
	public static final String PARAM_AMPERSAND = "&";
	public static final String PARAM_EQ = "=";
	public static final String PARAM_PROJECT = "project";
	public static final String PARAM_FROM_CANCEL = "fromCancel";
	public static final String PARAM_REFERRER = "referrer";
	
	
	// COOKIES 
	public static final String COOKIE_JSESSION_ID = "JSESSIONID";
	public static final String COOKIE_SITE_ID = "rep-man-siteId";
	public static final String COOKIE_IS_LOGGED_IN_WITH_SITE_ID = "rep-man-loggedInWithSiteId";
	public static final String COOKIE_DEFAULT_PATH = "/";
	
	// URL
	public static final String URL_POS_MANAGER_PREFIX = "http://upmyposrpt:9080/saptrv/te/posmanager/servlet/Login?siteID=";
	public static final String URL_POS_MANAGER_SUFFIX = "&userType=QA";
	public static final String URL_POS_MANAGER_DEFAULT_STORE = "0";
	
	
	// PARAMETER VALUES 
	public static final String PARAM_VALUE_LOGOUT = "logout";
	public static final String PARAM_VALUE_SESSION_TIMEOUT = "sessionTimeout";
	public static final String PARAM_VALUE_SEARCH_BY_TAG = "By Tag";
	public static final String PARAM_VALUE_SEARCH_BY_KEYWORD = "By Keyword";
}
