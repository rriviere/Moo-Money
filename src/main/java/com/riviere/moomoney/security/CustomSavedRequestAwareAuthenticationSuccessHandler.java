package com.riviere.moomoney.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import com.riviere.moomoney.domain.MooMoneyUser;

/**
 * A custom implementation of a successful login handler.
 * 
 * @author Richard Riviere
 * @date 28/01/2014
 */
public class CustomSavedRequestAwareAuthenticationSuccessHandler 
	extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Log logger = 
			LogFactory.getLog(CustomSavedRequestAwareAuthenticationSuccessHandler.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	public CustomSavedRequestAwareAuthenticationSuccessHandler(){
		super();
	}
	
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onAuthenticationSuccess(
    		HttpServletRequest request, 
    		HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
            	
    	MooMoneyUser user = (MooMoneyUser)authentication.getPrincipal();
   	 	logger.info("Successful login for user:" + user.getUsername() + " project:" + user.getProject());
   	 	
    	SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);
   	 
        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
    
}
