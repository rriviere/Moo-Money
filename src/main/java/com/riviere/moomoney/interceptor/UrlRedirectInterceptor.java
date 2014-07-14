package com.riviere.moomoney.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * Intercept urls as specified in the includes url list. Not used at present but keeping here
 * if required.
 * 
 * @author Richard Riviere
 * @date 08/05/2014
 */
public class UrlRedirectInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log logger = LogFactory
			.getLog(UrlRedirectInterceptor.class);
	
	private Map<String,String> intercepts = null;

	//before the actual handler will be executed
	public boolean preHandle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			Object handler) throws Exception {
 
		StringBuffer requestUrl = request.getRequestURL(); 
		// more elegant way
		for (Map.Entry<String, String> entry : intercepts.entrySet()) {
			String interceptUrl = entry.getKey();
			String redirectUrl = entry.getValue();
			if (requestUrl.toString().equals(interceptUrl)){
				response.sendRedirect(redirectUrl);
				logger.debug("intercepted: " + interceptUrl + " and redirected to:" + redirectUrl);
				return false;
			}			
		}
		return true; 
	}

	/**
	 * @return the intercepts
	 */
	public Map<String, String> getIntercepts() {
		return intercepts;
	}

	/**
	 * @param intercepts the intercepts to set
	 */
	public void setIntercepts(Map<String, String> intercepts) {
		this.intercepts = intercepts;
	}

}
