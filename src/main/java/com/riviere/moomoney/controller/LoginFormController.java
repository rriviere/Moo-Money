package com.riviere.moomoney.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.riviere.moomoney.constants.LovConstants;
import com.riviere.moomoney.exception.MooMoneyException;
import com.riviere.moomoney.form.LoginForm;
import com.riviere.moomoney.manager.LovManager;


/**
 * This is the controller for the login screen.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
public class LoginFormController {

	/**
	 * The lov manager
	 */
	@Autowired
	private LovManager lovManager;

	/**
	 * Return the login view
	 * 
	 * @param loginForm
	 *            the command object behind the form
	 * @param project
	 *            the site id/store on the url
	 * @param fullWindow
	 *            the screen mode
	 * @return the spring mvc view
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login(
			@ModelAttribute LoginForm loginForm,
			@RequestParam(value = "project", required = false) String project,
			@RequestParam(value = "fullWindow", required = false) String fullWindow)
			throws ServletRequestBindingException {

		return "login";
	}

	/**
	 * Return the login screen in error mode.
	 * 
	 * @param model
	 *            the spring mvc model
	 * @param project
	 *            the site id/store on the url
	 * @return the spring mvc view
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "/loginfailed.htm", method = RequestMethod.GET)
	public String loginError(
			ModelMap model,
			@RequestParam(value = "project", required = false) String project)
			throws ServletRequestBindingException {

		LoginForm loginForm = new LoginForm();
		loginForm.setProject(project);

		model.addAttribute("error", "true");
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	/**
	 * Return the login screen in logout mode.
	 * 
	 * @param loginForm
	 *            the command object behind the form
	 * @param project
	 *            the site id/store on the url
	 * @return the spring mvc view
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logout(
			@ModelAttribute LoginForm loginForm,
			@RequestParam(value = "project", required = false) String project,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletRequestBindingException {
		loginForm.setProject(project);
		return "login";
	}

	/**
	 * Return the login screen in session expired mode.
	 * 
	 * @param model
	 *            the spring mvc model
	 * @param project
	 *            the site id/store on the url
	 * @return the spring mvc view
	 * @throws ServletRequestBindingException
	 */
	@RequestMapping(value = "/sessionExpired.htm", method = RequestMethod.GET)
	public String sessionExpired(ModelMap model,
			@RequestParam(value = "project", required = false) String project)
			throws ServletRequestBindingException {

		LoginForm loginForm = new LoginForm();
		loginForm.setProject(project);

		model.addAttribute("sessionManagementErrorMessage",
				"Your session has expired");
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	/**
	 * Return the login screen in already logged in mode.
	 * 
	 * @param model
	 *            the spring mvc model
	 * @return the spring mvc view
	 */
	@RequestMapping(value = "/alreadyLoggedIn.htm", method = RequestMethod.GET)
	public String alreadyLoggedIn(ModelMap model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("sessionManagementErrorMessage",
				"Your user ID is already logged in");
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	/**
	 * Return the login screen in duplicate log in mode.
	 * 
	 * @param model
	 *            the spring mvc model
	 * @return the spring mvc view
	 */
	@RequestMapping(value = "/sessionExpiredDuplicateLogin.htm", method = RequestMethod.GET)
	public String sessionExpiredDuplicateLogin(ModelMap model) {
		LoginForm loginForm = new LoginForm();
		model.addAttribute("sessionManagementErrorMessage", "Duplicate log in");
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	/**
	 * Initialise the form.
	 * 
	 * @param model
	 *            the spring mvc model
	 * @return the spring mvc view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String initForm(ModelMap model) {
		LoginForm loginForm = new LoginForm();
		loginForm.setProject(LovConstants.PROJECT_ALL);
		model.addAttribute("loginForm", loginForm);
		return "login";
	}

	/**
	 * Get the stored list of values.
	 * 
	 * @return the stores list of values
	 * @throws MooMoneyException
	 */
	@ModelAttribute("projects")
	public Map<String, String> getStoreLov() throws MooMoneyException {
		Map<String, String> storeList = lovManager
				.getLov(LovConstants.LOV_PROJECT);
		return storeList;
	}
}