package com.riviere.moomoney.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.riviere.moomoney.dao.TagDao;
import com.riviere.moomoney.domain.Tag;
import com.riviere.moomoney.util.SessionUtils;


/**
 * This is the controller for the home screen.
 * All user objects in the session should be cleared when navigating to home.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
public class TagController {
	
	@Autowired
	TagDao tagDao;
	
	/**
	 * Handle successful login.
	 * 
	 * @param model the spring mvc model
	 * @param servletRequest
	 * @param servletResponse
	 * @return the spring mvc view
	 */
	@RequestMapping(value="/tags.htm", method = RequestMethod.GET)
	public ModelAndView fetchTags(final HttpServletRequest servletRequest,
			final HttpServletResponse servletResponse) {
		System.out.println("inside controller");
		List<Tag> tags = tagDao.fetchAllTag();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("tags", tags);
		System.out.println("inside tags:"+tags);
		// clear the  session
		return new ModelAndView("taglist", model);
	}
}
