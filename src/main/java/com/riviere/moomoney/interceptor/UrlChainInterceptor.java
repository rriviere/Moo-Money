package com.riviere.moomoney.interceptor;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.riviere.moomoney.util.RequestUtils;
import com.riviere.moomoney.util.SessionUtils;
import com.riviere.moomoney.util.URLHolder;


/**
 * Intercept urls as specified in the includes url list.
 * 
 * Create a breadcumb of urls used to indicate what pages 
 * were accessed previously and in what order.
 * 
 * @author Richard Riviere
 * @date 08/05/2014
 */
public class UrlChainInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log logger = LogFactory
			.getLog(UrlChainInterceptor.class);
	
	/**
	 * Key that define the URL list stored in the session.
	 */
	public static final String URL_LIST = "URL_LIST";

	/**
	 * List of bean mappings to be included in the cancel URLs.
	 */
	private List includes = null;
	
	/**
	 * List of bean mappings to be excluded in cancel URLs.
	 */
	private List excludes = null;
	
	/**
	 * Map of excluded beans to be included on certain condition
	 */
	private Map ignoreExclude = null;

	/**
	 * List of URLs that are disallowed to have duplicates entries in the URL list
	 */
	private List disallowedDuplicates = null;
	
	/**
	 * If the same multiple URLs are are allowed to added in a row
	 */
	private boolean allowRepeated = false;
	
	/**
	 * List of bean mappings to exclude URL params when stored in URL list.
	 */
	private List ignoreQueryString = null;

	/**
	 * List of mappers that define bean mappings and the session attributes
	 * (keys) that needs to be cleared after processing the given bean mapping
	 * (url).
	 */
	private Map invalidateSessionAttributes = null;

	
	public void postHandle(
			HttpServletRequest request,
			HttpServletResponse response, 
			Object handler,
			ModelAndView modelAndView) throws Exception {

		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();

		List urlList = (List) SessionUtils.get(session, URL_LIST);
		if (urlList == null) {
			urlList = Collections.synchronizedList(new Vector());

		}
		if (logger.isDebugEnabled()) {
			logger.debug("getRequestURL : " + request.getRequestURL());
			logger.debug("getServletPath : " + request.getServletPath());
			logger.debug("getQueryString : " + request.getQueryString());
		}

		if (invalidateSessionAttributes.containsKey(servletPath)) {
			List list = (List) invalidateSessionAttributes.get(servletPath);
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				if (logger.isDebugEnabled()) {
					logger.debug("Removing session attribute : " + key);
				}
				SessionUtils.remove(session, key);
			}
		}

		boolean included = includes.contains(servletPath);
		if (included || excludes.contains(servletPath)) {
			StringBuffer requestURL = request.getRequestURL();
			
			if (!ignoreQueryString.contains(servletPath)
					&& request.getQueryString() != null) {

				requestURL.append("?");
				requestURL.append(request.getQueryString());
				if (logger.isDebugEnabled()) {
					logger.debug("Request URL with query params: " + requestURL);
				}
			}			
			
			if(!urlList.isEmpty()){
				URLHolder previousURL = (URLHolder) urlList.get(urlList.size() - 1);				
				if(servletPath != null && servletPath.equals(ignoreExclude.get(previousURL.getServletPath()))){					
					previousURL.setIncluded(true);					
				}				
			}			
			
			URLHolder urlHolder = new URLHolder(requestURL.toString(), included);		

			if (!urlList.isEmpty()) {
				URLHolder previousURL = (URLHolder) urlList
						.get(urlList.size() - 1);

				// Check if the url exists in the list already
				boolean isDuplicate = false;
				for (int i = urlList.size() - 1; i >= 0; i--) {
					URLHolder current = (URLHolder) urlList.get(i);
					if (RequestUtils.equalsIgnoreParameters(current.getUrl(),
							urlHolder.getUrl())) {
						isDuplicate = true;
						break;
					}					
				}

				if (!isDuplicate) {
					urlList.add(urlHolder);
				} else {
					boolean isRepeated = RequestUtils.equalsIgnoreParameters(
							previousURL.getUrl(), urlHolder.getUrl());

					// Check if the url is disallowed to be added multiple times
					boolean isDisallowedDuplicate = false;
					if (!disallowedDuplicates.isEmpty()) {
						for (int i = 0; i < disallowedDuplicates.size(); i++) {
							String duplicateURL = (String) disallowedDuplicates
									.get(i);
							if (duplicateURL.equalsIgnoreCase(servletPath)) {
								isDisallowedDuplicate = true;
								break;
							}
						}
					}

					if (isRepeated) {
						if (allowRepeated && !isDisallowedDuplicate) {
							urlList.add(urlHolder);
						}
					} else {
						if (!isDisallowedDuplicate) {
							urlList.add(urlHolder);
						}
					}
				}
			} else {
				urlList.add(urlHolder);
			}
		}		
		SessionUtils.set(session, URL_LIST, urlList);
	}


	/**
	 * @return the includes
	 */
	public List getIncludes() {
		return includes;
	}


	/**
	 * @param includes the includes to set
	 */
	public void setIncludes(List includes) {
		this.includes = includes;
	}


	/**
	 * @return the excludes
	 */
	public List getExcludes() {
		return excludes;
	}


	/**
	 * @param excludes the excludes to set
	 */
	public void setExcludes(List excludes) {
		this.excludes = excludes;
	}


	/**
	 * @return the ignoreExclude
	 */
	public Map getIgnoreExclude() {
		return ignoreExclude;
	}


	/**
	 * @param ignoreExclude the ignoreExclude to set
	 */
	public void setIgnoreExclude(Map ignoreExclude) {
		this.ignoreExclude = ignoreExclude;
	}


	/**
	 * @return the disallowedDuplicates
	 */
	public List getDisallowedDuplicates() {
		return disallowedDuplicates;
	}


	/**
	 * @param disallowedDuplicates the disallowedDuplicates to set
	 */
	public void setDisallowedDuplicates(List disallowedDuplicates) {
		this.disallowedDuplicates = disallowedDuplicates;
	}


	/**
	 * @return the allowRepeated
	 */
	public boolean isAllowRepeated() {
		return allowRepeated;
	}


	/**
	 * @param allowRepeated the allowRepeated to set
	 */
	public void setAllowRepeated(boolean allowRepeated) {
		this.allowRepeated = allowRepeated;
	}


	/**
	 * @return the ignoreQueryString
	 */
	public List getIgnoreQueryString() {
		return ignoreQueryString;
	}


	/**
	 * @param ignoreQueryString the ignoreQueryString to set
	 */
	public void setIgnoreQueryString(List ignoreQueryString) {
		this.ignoreQueryString = ignoreQueryString;
	}


	/**
	 * @return the invalidateSessionAttributes
	 */
	public Map getInvalidateSessionAttributes() {
		return invalidateSessionAttributes;
	}


	/**
	 * @param invalidateSessionAttributes the invalidateSessionAttributes to set
	 */
	public void setInvalidateSessionAttributes(Map invalidateSessionAttributes) {
		this.invalidateSessionAttributes = invalidateSessionAttributes;
	}


	/**
	 * @return the urlList
	 */
	public static String getUrlList() {
		return URL_LIST;
	}
}
