package com.riviere.moomoney.controller.ui;

import org.springframework.ui.ModelMap;

/**
 * This is the Abstract Vertical Navigation Controller which all other 
 * vertical navigation controllers
 * should extend off. It will set the active menu item in the vertical navigation.
 * 
 * @author Richard Riviere
 * @date 04/03/2014
 */
public abstract class AbstractVerticalNavController extends AbstractController {
	
	/**
	 * Show the view with the active vertical menu item
	 * 
	 * @param model
	 */
	protected void showForm(ModelMap model){
		setActiveMenuItem(model);
		setActiveSubMenuItem(model);
	}
	
	/**
	 * Get the active vertical menu item
	 * 
	 * @return
	 */
	public abstract String getActiveMenuItem();

	/**
	 * Get the active vertical sub-menu item
	 * 
	 * @return
	 */
	public abstract String getActiveSubMenuItem();
	
	/**
	 * Set the active vertical menu item
	 * 
	 * @param model
	 */
	protected void setActiveMenuItem(ModelMap model){
		model.addAttribute("activeMenuItem", getActiveMenuItem());
	}
	
	/**
	 * Set the active vertical menu item
	 * 
	 * @param model
	 */
	protected void setActiveSubMenuItem(ModelMap model){
		model.addAttribute("activeSubMenuItem", getActiveSubMenuItem());
	}	
}
