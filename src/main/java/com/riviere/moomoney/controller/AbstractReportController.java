package com.riviere.moomoney.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;

import com.riviere.moomoney.constants.BirtConstants;
import com.riviere.moomoney.constants.LovConstants;
import com.riviere.moomoney.domain.MooMoneyUser;
import com.riviere.moomoney.form.AbstractReportForm;
import com.riviere.moomoney.util.SessionUtils;

/**
 * This is the Abstract Report Controller which all other reporting controllers
 * should extend off.
 *  
 * @author Richard Riviere
 * @date 24/04/2014
 */
public abstract class AbstractReportController extends AbstractVerticalNavController {
	
	/**
	 * The key used to store session objects for this class
	 * 
	 * @return
	 */
	public abstract String getSessionKey();
	
	/**
	 * Set defaults for report parameters specific to the report
	 * 
	 * @param reportParams
	 */
	public abstract void defaultReportParams(LinkedHashMap<String,String> reportParams);
	
	/**
	 * Populate report parameters specific to the report
	 * 
	 * @param reportParams
	 * @param form
	 */
	public abstract void populateReportParams(LinkedHashMap<String,String> reportParams, AbstractReportForm form);
	
	/**
	 * Get the report name
	 * 
	 * @return the report name
	 */
	protected abstract String getReportName();
	
	/**
	 * Get the report title
	 * 
	 * @return the report title
	 */
	protected abstract String getReportTitle();


	/**
	 * Abstract process form submit
	 * 
	 * @param abstractReportForm
	 * @param servletRequest
	 * @throws ServletRequestBindingException
	 */
	protected void processSubmit (
			AbstractReportForm abstractReportForm,
			ModelMap modelMap,
			HttpServletRequest servletRequest) throws ServletRequestBindingException {
		clearSessionByKey(servletRequest);
		SessionUtils.set(servletRequest.getSession(), getSessionKey(), abstractReportForm);
		
		LinkedHashMap<String,String> reportParams 
			= new LinkedHashMap<String, String>();
		
		defaultReportParams(reportParams);
		addStandardReportParams(abstractReportForm, reportParams);		
		populateReportParams(reportParams,abstractReportForm);
		
		addReportNameToModel(getReportName(), modelMap);
		addReportTitleToModel(getReportTitle(), modelMap);
		addReportParamsToModel(modelMap, reportParams);	
	}	
	
	
	/* (non-Javadoc)
	 * @see com.myer.reporting.controller.AbstractVerticalNavController#showForm(org.springframework.ui.ModelMap)
	 */
	protected void showForm(ModelMap model){
		super.showForm(model);
		addStandardModelAttributes(model);
	}
	
	/**
	 * Clear the session by the session key
	 * 
	 * @param servletRequest
	 * @throws ServletRequestBindingException
	 */
	protected void clearSessionByKey(HttpServletRequest servletRequest) throws ServletRequestBindingException {
		SessionUtils.remove(servletRequest.getSession(), getSessionKey());
	}

	/**
	 * Add any standard model attributes
	 * @param model
	 */
	protected void addStandardModelAttributes(ModelMap model){
					
	}

	/**
	 * The default pos user id
	 * 
	 * @param reportParams
	 */
	public void defaultUserNameReportParam(LinkedHashMap<String, String> reportParams) {
		reportParams.put(BirtConstants.PARAM_POS_ID, null);  	
	}
	
	/**
	 * Default the user store report parameter
	 * 
	 * @param reportParams
	 */
	public void defaultUserStoreReportParam(LinkedHashMap<String, String> reportParams) {
		reportParams.put(BirtConstants.PARAM_POS_STORE, null);	
	}	
	
	/**
	 * Default the user display name report parameter
	 * 
	 * @param reportParams
	 */
	public void defaultUserDisplayNameReportParam(LinkedHashMap<String, String> reportParams) {
		reportParams.put(BirtConstants.PARAM_POS_DISPLAY_NAME, null);  	
	}		
	
	/**
	 * Add report parameters that are standard to most reports
	 * 
	 * @param abstractReportForm
	 * @param reportParams
	 */
	protected void addStandardReportParams (
			AbstractReportForm abstractReportForm, 
			LinkedHashMap<String,String> reportParams){
		
		addUserNameToReportParams(reportParams);
		addUserStoreToReportParams(abstractReportForm, reportParams);		
		addUserDisplayNameToReportParams(reportParams);			
	}
	
	/**
	 * Add pos user id to report parameters
	 * 
	 * @param params
	 */
	protected void addUserNameToReportParams(LinkedHashMap<String,String> params)	{
		defaultUserNameReportParam(params);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    MooMoneyUser user = (MooMoneyUser)auth.getPrincipal();
	    String username = user.getUsername();
	    if (username != null){
	    	params.put(BirtConstants.PARAM_POS_ID, username);  	
	    }
	}
	
	/**
	 * Add store from form(if exists) or from security context to the report parameters
	 * 
	 * @param form
	 * @param params
	 */
	protected void addUserStoreToReportParams(AbstractReportForm form,LinkedHashMap<String,String> params){
		defaultUserStoreReportParam(params);
		String storeStr = form.getStore();
		if (StringUtils.isNotEmpty(storeStr)){
	    	params.put(BirtConstants.PARAM_POS_STORE, storeStr);  	
	    }else{
	    	addUserStoreToReportParams(params);	    	
	    }
	}
	
	/**
	 * Add the user store to the report parameters
	 * 
	 * @param params
	 */
	protected void addUserStoreToReportParams(LinkedHashMap<String,String> params)	{
		defaultUserStoreReportParam(params);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    MooMoneyUser user = (MooMoneyUser)auth.getPrincipal();
	    String projectStr = user.getProject();
	    /*if (StringUtils.isNotEmpty(storeStr) && !storeStr.equalsIgnoreCase(LovConstants.LOV_STORE_NONE)){
	    	params.put(BirtConstants.PARAM_POS_STORE, projectStr);  	
	    }*/
	}		
	
	/**
	 * Add the user display name to the report parameters
	 * 
	 * @param params
	 */
	protected void addUserDisplayNameToReportParams(LinkedHashMap<String,String> params)	{
		defaultUserDisplayNameReportParam(params);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    MooMoneyUser user = (MooMoneyUser)auth.getPrincipal();
	    String firstname = user.getFirstname();
	    String lastname = user.getLastname();
	    if (firstname !=null && lastname !=null){
	    	String userDisplayName = firstname + " " + lastname;
	    	params.put(BirtConstants.PARAM_POS_DISPLAY_NAME, userDisplayName);  	
	    }
	}	
	
	/**
	 * Add the report parameters to the model
	 * 
	 * @param modelMap
	 * @param reportParams
	 */
	protected void addReportParamsToModel(ModelMap modelMap, LinkedHashMap<String,String> reportParams)	{
		modelMap.put("reportParams", reportParams);
	}	
	
	/**
	 * Add the report name to the model
	 * 
	 * @param reportName
	 * @param modelMap
	 */
	protected void addReportNameToModel(String reportName, ModelMap modelMap)	{
		modelMap.put("reportName", reportName);
	}	
	
	/**
	 * Add the report title to the model
	 * 
	 * @param reportTitle
	 * @param modelMap
	 */
	protected void addReportTitleToModel(String reportTitle, ModelMap modelMap)	{
		modelMap.put("reportTitle", reportTitle);
	}		
	
}
