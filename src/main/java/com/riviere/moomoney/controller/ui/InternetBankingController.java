package com.riviere.moomoney.controller.ui;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.riviere.moomoney.util.SessionUtils;


/**
 * This is the controller for the home screen.
 * All user objects in the session should be cleared when navigating to home.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
@RequestMapping(value = "/internetBanking.htm")
public class InternetBankingController {
	
	
	/**
	 * Handle successful login.
	 * 
	 * @param model the spring mvc model
	 * @param servletRequest
	 * @param servletResponse
	 * @return the spring mvc view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(
			ModelMap model,
			final HttpServletRequest servletRequest,
			final HttpServletResponse servletResponse) {
		
		// clear the session
		return "internetBanking";
	}
}
