package com.riviere.moomoney.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.riviere.moomoney.constants.ParamConstants;
import com.riviere.moomoney.domain.MooMoneyUser;
import com.riviere.moomoney.util.WebUtils;

/**
 * A custom implementation of a logout handler.
 * This is required to pass on the users siteId when it is used on login.
 * 
 * @author Richard Riviere
 * @date 28/01/2014
 */
public class CustomUrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler  {
	
	private static final Log logger = 
			LogFactory.getLog(CustomUrlLogoutSuccessHandler.class);
	
	/**
	 * Create a custom url logout success handler
	 */
	public CustomUrlLogoutSuccessHandler() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public void onLogoutSuccess(
			final HttpServletRequest servletRequest, 
			final HttpServletResponse servletResponse, 
			final Authentication authentication) throws IOException, ServletException {
		
		String targetUrl = determineTargetUrl(servletRequest, servletResponse);
		
        if (servletResponse.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
	    MooMoneyUser user = (MooMoneyUser)authentication.getPrincipal();
	    String store = user.getProject();
	    
        String amendedTargetUrl = null;
        if (user.getProject() != null){
        	amendedTargetUrl = 
        			targetUrl.concat(
            		ParamConstants.PARAM_PREFIX + 
            		ParamConstants.PARAM_PROJECT + 
            		ParamConstants.PARAM_EQ +
            		store);
        	
        }else{
        	amendedTargetUrl = targetUrl;        	
        } 
        
        servletRequest.getSession().invalidate(); 
        WebUtils.eraseCookie(ParamConstants.COOKIE_JSESSION_ID, servletRequest, servletResponse);        
        logger.info("Successful logout for user:" + user.getUsername());
        getRedirectStrategy().sendRedirect(servletRequest, servletResponse, amendedTargetUrl);
	}	

}
