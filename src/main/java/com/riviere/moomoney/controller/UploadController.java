package com.riviere.moomoney.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.riviere.moomoney.domain.FileMeta;
import com.riviere.moomoney.manager.FilesManager;


/**
 * This is the controller for the home screen.
 * All user objects in the session should be cleared when navigating to home.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
@RequestMapping(value = "/fileUploads.htm")
public class UploadController {
	
	@Autowired
	FilesManager filesManager;
	
	/**
	 * Handle successful login.
	 * 
	 * @param model the spring mvc model
	 * @param servletRequest
	 * @param servletResponse
	 * @return the spring mvc view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String showForm (
			ModelMap model,
			final HttpServletRequest servletRequest,
			final HttpServletResponse servletResponse) {
		List<FileMeta> files = filesManager.listAllFiles();
		model.addAttribute("files", files);
		return "uploads";
	}
	
	
}
