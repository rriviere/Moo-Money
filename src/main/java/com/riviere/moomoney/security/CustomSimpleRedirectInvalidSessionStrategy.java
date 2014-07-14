package com.riviere.moomoney.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.riviere.moomoney.constants.ParamConstants;
import com.riviere.moomoney.util.WebUtils;

/**
 * A custom InvalidSessionStrategy. 
 * This is required to pass on the users siteId when it is used on login. 
 * 
 * @author Richard Riviere
 * @date 13/02/2014
 */
public final class CustomSimpleRedirectInvalidSessionStrategy implements InvalidSessionStrategy {
    private final Log logger = LogFactory.getLog(getClass());
    private final String destinationUrl;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private boolean createNewSession = true;

    /**
     * @param invalidSessionUrl the invalid session url
     */
    public CustomSimpleRedirectInvalidSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.web.session.InvalidSessionStrategy#onInvalidSessionDetected(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {            	
        String amendedTargetUrl = destinationUrl;           	
  
        String loggedInWithSiteIdCookie = WebUtils.getCookieValue(ParamConstants.COOKIE_IS_LOGGED_IN_WITH_SITE_ID,request);
        boolean loggedInWithSiteId = false;
        if (loggedInWithSiteIdCookie != null){
        	loggedInWithSiteId = Boolean.parseBoolean(loggedInWithSiteIdCookie);
        }
        		
		String siteIdCookie = WebUtils.getCookieValue(ParamConstants.COOKIE_SITE_ID,request);
		if (siteIdCookie != null && loggedInWithSiteId) {
			amendedTargetUrl = 
					destinationUrl.concat(
	            	ParamConstants.PARAM_PREFIX 
	            	+ ParamConstants.PARAM_PROJECT 
	            	+ ParamConstants.PARAM_EQ 
	            	+ siteIdCookie);
	    }else {
        	amendedTargetUrl = destinationUrl; 	    	        	
	    } 			
		
        if (createNewSession) {
            request.getSession();
        }
        logger.debug("Starting new session (if required) and redirecting to '" + destinationUrl + "'");
        redirectStrategy.sendRedirect(request, response, amendedTargetUrl);
    }
	
    /**
     * Determines whether a new session should be created before redirecting (to avoid possible looping issues where
     * the same session ID is sent with the redirected request). Alternatively, ensure that the configured URL
     * does not pass through the {@code SessionManagementFilter}.
     *
     * @param createNewSession defaults to {@code true}.
     */
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}

