package com.riviere.moomoney.controller.ui;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is the controller for the error screen.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
public class ErrorController {

	/**
	 * Get some generic error attributes and pass onto the view for display.
	 * 
	 * @param request the http request object
	 * @param response the http response object
	 * @param model the view model
	 * @return the view
	 */
	@RequestMapping(value = "/error.htm", method = RequestMethod.GET)
	public String customError(
			HttpServletRequest request,
			HttpServletResponse response, 
			Model model) {
		
		// retrieve some useful information from the request
		Integer statusCode = (Integer) request
			.getAttribute("javax.servlet.error.status_code");
		
		Throwable throwable = (Throwable) request
			.getAttribute("javax.servlet.error.exception");
		// String servletName = (String)
		// request.getAttribute("javax.servlet.error.servlet_name");
		String exceptionMessage = getExceptionMessage(throwable, statusCode);
	
		String requestUri = (String) request
				.getAttribute("javax.servlet.error.request_uri");
		
		if (requestUri == null) {
			requestUri = "Unknown";
		}
	
		String message = 
				MessageFormat.format(
						"{0} returned for {1} with message {3}", statusCode,requestUri, exceptionMessage);
	
		model.addAttribute("errorCode", statusCode);
		model.addAttribute("errorMessage", message);
		return "error";
	}

	private String getExceptionMessage(Throwable throwable, Integer statusCode) {
		if (throwable != null) {
			Throwable rootCause = ExceptionUtils.getRootCause(throwable);
			if (rootCause != null){
				String rootMessage = rootCause.getMessage();
				if (StringUtils.isNotEmpty(rootMessage)){
					return rootMessage;
				}
			}
		}
		HttpStatus httpStatus = HttpStatus.valueOf(statusCode);
		if (httpStatus != null){
			return httpStatus.getReasonPhrase();
		} 
		return "Unknown";		
	}

}