package com.riviere.moomoney.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.riviere.moomoney.constants.SecurityConstants;

/**
 * A two factor authentication filter. Second factory is a users store.
 * 
 * @author Richard Riviere
 * @date 14/01/2014
 */
public class TwoFactorAuthenticationFilter extends UsernamePasswordAuthenticationFilter{  
	
	
	public TwoFactorAuthenticationFilter(){
		super();
	}
	
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#obtainUsername(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected String obtainUsername(HttpServletRequest request) {
        
    	String username = super.obtainUsername(request);
        String project = request.getParameter(SecurityConstants.PROJECT_PARAM);        
        String combinedUsername = 
        		username + 
        		SecurityConstants.TWO_FACTOR_AUTHENTICTION_DELIM + 
        		project;
        
        return combinedUsername;
    }
    
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
        String usernameParameter = obtainUsername(request);
        String password = obtainPassword(request);
        
		String username=null;			    
		String[] split = usernameParameter.split(SecurityConstants.TWO_FACTOR_AUTHENTICTION_DELIM);		
		if (split != null){
			if(split.length == 1){
				username = usernameParameter;
			}
			else {
				username = split[0];
			}					
		}

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }
        
        username = username.trim();
        UsernamePasswordAuthenticationToken authRequest
        	= new UsernamePasswordAuthenticationToken(usernameParameter,password);     
	    setDetails(request, authRequest);

	    Authentication authentication = super.getAuthenticationManager().authenticate(authRequest);    
	    return authentication;
	}
}
