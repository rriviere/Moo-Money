package com.riviere.moomoney.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.MDC;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.filter.GenericFilterBean;

import com.riviere.moomoney.constants.MDCKeyConstants;

/**
 * Spring Security Filter to set user attributes into the MDC for logging purposes.
 * 
 * This filter must occur after the <code>HttpSessionContextIntegrationFilter</code> to ensure
 * that the <code>SecurityContextHolder</code> has been populated.
 * 
 * @author Richard Riviere
 * @date 21/05/2012
 *
 */
public class SecurityLoggingFilter extends GenericFilterBean {

	private SessionRegistry sessionRegistry;
	
	/** Default filter order */
	private int order = 1;

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) 
			throws IOException, ServletException {
		// user id
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null && context.getAuthentication() != null)	{
			MDC.put(MDCKeyConstants.KEY_USER_ID, context.getAuthentication().getName());
		} else	{
			MDC.put(MDCKeyConstants.KEY_USER_ID, MDCKeyConstants.DEFAULT_USER_ID);
		}
		
		// remote address
		MDC.put(MDCKeyConstants.KEY_REMOTE_ADDR, request.getRemoteAddr());		
		
		if (logger.isDebugEnabled()){
			logger.debug("MDC keys setup for spring security filter");
		}
		
		// concurrent users
		List<Object> principals = sessionRegistry.getAllPrincipals();
		MDC.put(MDCKeyConstants.CONCURRENT_USER_COUNT, principals.size());	
		
		if (logger.isDebugEnabled()){
			logger.debug("MDC keys setup for spring security filter");
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * Set the filter order
	 * @param order an integer representing the position of the filter 
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * The filter order
	 * @return an integer representing the position of the filter 
	 */
	public int getOrder() {
		return order;
	}
	
	/**
	 * Set the spring security session registry 
	 * @return the sessionRegistry spring security session registry 
	 */
	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	/**
	 * Get the spring security session registry 
	 * @param sessionRegistry spring security session registry 
	 */
	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}
}
