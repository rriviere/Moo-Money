package com.riviere.moomoney.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * This is the controller for the home screen.
 * All user objects in the session should be cleared when navigating to home.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
@RequestMapping(value = "/importExpenses.htm")
public class ImportExpensesController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String home(
			ModelMap model,
			final HttpServletRequest servletRequest,
			final HttpServletResponse servletResponse) {
		
		return "importExpenses";
	}
}
