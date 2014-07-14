package com.riviere.moomoney.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestUtils;

import com.riviere.moomoney.constants.ParamConstants;
import com.riviere.moomoney.constants.SecurityConstants;

/**
 * A custom implementation of an unsuccessful login handler.
 * This is required to pass on the users siteId when it is used on login.
 * 
 * @author Richard Riviere
 * @date 28/01/2014
 */
public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private static final Log logger = 
			LogFactory.getLog(CustomSimpleUrlAuthenticationFailureHandler.class);
		
	/**
	 * Default logout failure url
	 */
	private String defaultFailureUrl;    
	
	/**
	 * Create logout failure instance
	 */
	public CustomSimpleUrlAuthenticationFailureHandler() {
	    super();
	}
	
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    public void onAuthenticationFailure(
    		HttpServletRequest request, 
    		HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
    	
    	String userName = ServletRequestUtils.getStringParameter(request,SecurityConstants.SPRING_USERNAME_PARAM);
    	String project = ServletRequestUtils.getStringParameter(request,SecurityConstants.PROJECT_PARAM);
     
    	if (defaultFailureUrl == null) {
            logger.debug("No failure URL set, sending 401 Unauthorized error");

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
            
        } else {
            saveException(request, exception);
	        if (exception.getClass().getSimpleName().equals("UsernameNotFoundException")) {
					// It happens when information provided  is not sufficient
	        	logger.info("UsernameNotFoundException for User:" + userName);
	        }              
            if (exception.getClass().getSimpleName().equals("SessionAuthenticationException")) {
                // It happens when max allowed Sessions for user exceeds   
            	logger.info("SessionAuthenticationException for User:" + userName);
	        }
	        if (exception.getClass().getSimpleName().equals("DisabledException")) {
					// It happens when enabled property of User(Spring calss) get false value
	        	logger.info("DisabledException for User:" + userName);
	        }
	        if (exception.getClass().getSimpleName().equals("BadCredentialsException")) {
					// It happens when either username or password is wrong
	        	logger.info("BadCredentialsException for User:" + userName);
	        }   	        
	        if (exception.getClass().getSimpleName().equals("InsufficientAuthenticationException")) {
					// It happens when information provided  is not sufficient
	        	logger.info(exception.getMessage() + userName);
	        }  	        
            logger.debug("Redirecting to " + defaultFailureUrl);
            RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();            
            
            String amendedDafultUrl = null;
            if (project == null){
	            amendedDafultUrl = 
	            		defaultFailureUrl.concat(
	            		ParamConstants.PARAM_PREFIX + 
	            		ParamConstants.PARAM_PROJECT + 
	            		ParamConstants.PARAM_EQ 
	            		+ project);
            }else{
	            amendedDafultUrl = defaultFailureUrl;           	
            }
            redirectStrategy.sendRedirect(request, response, amendedDafultUrl);       
        }
    }

	/**
	 * @return the defaultFailureUrl
	 */
	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	/**
	 * @param defaultFailureUrl the defaultFailureUrl to set
	 */
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
    
}
