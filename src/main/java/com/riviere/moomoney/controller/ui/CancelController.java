package com.riviere.moomoney.controller.ui;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.riviere.moomoney.interceptor.UrlChainInterceptor;
import com.riviere.moomoney.util.RequestUtils;
import com.riviere.moomoney.util.SessionUtils;
import com.riviere.moomoney.util.URLHolder;

/**
 * The cancel/back controller used by a number of views.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
@RequestMapping(value = "/cancel.htm")
public class CancelController {

	/**
	 * The logger for this class
	 */
	private static final Log logger = LogFactory.getLog(CancelController.class);
	
	/**
	 * The fail view if the cancel action does not work
	 */
	private String failView = null;
		
	/**
	 * From cancel url action 
	 */
	private static final String FROM_CANCEL = "fromCancel=true";
	
	/**
	 * Delimeter for query string in URL
	 */
	private static final String QUERY_STRING_DELIMETER = "?";
	
	/**
	 * Delimeter for query string attributes in URL
	 */
	private static final String QUERY_STRING_ATTRIBUTE_DELIMETER = "&";

	/**
	 * Handle a request to the cancel controller and lookup the url bread crumbs for
	 * the most previous controller.
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * Note: The referer in the HTTP Header is unreliable because sometimes
		 * it is not added by the browser for security reasons (i.e. entering a
		 * URL in the address bar is the same as window.location.href in
		 * JavaScript) and firewalls, proxies and security systems are known to
		 * strip or replace the referer the HTTP header. Therefore we don't use
		 * request.getHeader("referer")
		 */
		
		// Retrieve custom referer from the HTTP request query string. See header.jsp
		String referer = request.getParameter("referer");
		List urlList = (List)SessionUtils.get(request.getSession(), UrlChainInterceptor.URL_LIST);
		
		if (referer == null || (referer != null && referer.trim().length() == 0)) {
			logger.info("Referer not set in request for cancel");
			pruneList(0, urlList);
			return  new ModelAndView(this.getFailView());
		}
		
		int index = calculateIndex(referer, urlList);
		
		URLHolder redirectURL = null;
		if (index >= 0) {
			redirectURL = ((URLHolder) urlList.get(index));
		}
		
		if (redirectURL == null) {
			// If the redirect URL cannot be found, clear out the list first and
			// then redirect to the fail view. No error messages required but
			// safer to at least put an information message so we can monitor
			// this occurence.
			logger.info("Cannot find redirect URL for cancel");
			pruneList(0, urlList);
			return  new ModelAndView(this.getFailView());
		}

		pruneList(index, urlList);
		
		// Add from cancel query string attribute
		String url = redirectURL.getUrl();
		if (!StringUtils.contains(url, FROM_CANCEL)) {
			if (!StringUtils.contains(url, QUERY_STRING_DELIMETER)) {
				url += QUERY_STRING_DELIMETER;
			} else {
				url += QUERY_STRING_ATTRIBUTE_DELIMETER;
			}
			url += FROM_CANCEL;
		}
		return new ModelAndView(new RedirectView(url));
	}
	
	/**
	 * The list is built up using the interceptor. In most cases the last entry
	 * in the list will be the URL we are cancelling from. We can only cancel to
	 * URLs that are included. The index will be the last URL in the list that
	 * is included for cancelling.
	 * 
	 * @param referer
	 *            The referer that called the cancel action.
	 * @param urlList
	 *            The list of URLs from the session.
	 * @return The index of the URL we will return to.
	 */
	private int calculateIndex(String referer, List urlList) {
		int index = -1;
		if (urlList!=null && !urlList.isEmpty()) {
			for (int i = urlList.size() - 1; i >= 0; i--) {
				URLHolder current = (URLHolder) urlList.get(i);
				String url = current.getUrl();
				if (current.isIncluded()
						&& RequestUtils.equalsIgnoreParameters(url, referer)) {
					index = i;
					break;
				}
			}
		}

		return index;
	}
	
	/**
	 * This method prunes the list of URLs by removing all URLS starting 
	 * from the supplied index.
	 * @param index The index to be prune from.
	 * @param urlList The list of URLs.
	 */
	private void pruneList(int index, List urlList) {
		if (index < 0) {
			index = 0;
		}
		if (urlList!=null && !urlList.isEmpty()) {
			int size = urlList.size();
			for (int i = size - 1; i >= index; i--) {
				urlList.remove(i);
			}
		}
	}

	/**
	 * Get the fail view if the cancel action does not work
	 * 
	 * @return the failView
	 */
	public String getFailView() {
		return failView;
	}

	/**
	 * Set the fail view if the cancel action does not work
	 * 
	 * @param failView
	 */
	public void setFailView(String failView) {
		this.failView = failView;
	}
}
