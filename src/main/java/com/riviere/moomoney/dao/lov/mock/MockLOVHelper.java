package com.riviere.moomoney.dao.lov.mock;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.riviere.moomoney.constants.LovConstants;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * Holds static list of values
 * 
 * @author Richard Riviere
 * @date 08/05/2014
 */
public class MockLOVHelper {

	public static final String ASC_CODE =  "ASC";
	public static final String ASC_DESC =  "Oldest to Newest";	
	
	public static final String DESC_CODE =  "DESC";
	public static final String DESC_DESC =  "Newest to Oldest";	
	
	public static final String SINGLE_CODE =  "single";
	public static final String SINGLE_DESC =  "Single";
	
	public static final String TEAM_MEMBER_CODE =  "teamMember";
	public static final String TEAM_MEMBER_DESC =  "Team Member";	
	
	public static final String REGISTER_CODE =  "register";
	public static final String REGISTER_DESC =  "Register";	
	
	public static final String NO_SALES_CODE =  "nosales";
	public static final String NO_SALES_DESC =  "No Sales";	
	
	public static final String LOGIN_CODE =  "loginattempts";
	public static final String LOGIN_DESC =  "Login Attempts";		
	
	public static final String RETURN_CODE =  "returneditems";
	public static final String RETURN_DESC =  "Returned Items";	
	
	public static final String NONSCANNED_CODE =  "nonscanneditems";
	public static final String NONSCANNED_DESC =  "Non-Scanned Items";	
	
	public static final String PRICEOVERRIDE_CODE =  "priceoverrides";
	public static final String PRICEOVERRIDE_DESC =  "Price Overrides";		
	
	public static final String ITEMVOIDS_CODE =  "itemvoids";
	public static final String ITEMVOIDS_DESC =  "Item voids";		
	
	public static final String TRANVOIDS_CODE =  "transactionvoids";
	public static final String TRANVOIDS_DESC =  "Transaction voids";		
	
	public static final String POSTVOIDS_CODE =  "postvoids";
	public static final String POSTVOIDS_DESC =  "Post voids";	
	
	public static final String TENDPOSTVOIDS_CODE =  "tendermgmtpostvoids";
	public static final String TENDPOSTVOIDS_DESC =  "Tender mgmt. post voids";	
	
	public static final String PREVIOUS_BUSINESS_DAY_CODE =  "previous";
	public static final String PREVIOUS_BUSINESS_DAY_DESC =  "Previous Business Day";
	
	public static final String SELECTED_BUSINESS_DAY_CODE =  "selected";
	public static final String SELECTED_BUSINESS_DAY_DESC =  "Selected Business Day";
	
	public static final String RANGE_BUSINESS_DAY_CODE =  "range";
	public static final String RANGE_BUSINESS_DAY_DESC =  "Business Day Range";	
	
	public static final String WEEK_ENDING_BUSINESS_DAY_CODE =  "weekEnding";
	public static final String WEEK_ENDING_DAY_DESC =  "Week Ending Day";
	
	public static final String ALL_CODE =  "all";
	public static final String ALL_DESC =  "All";
	
	public static final String POSREGISTER_CODE =  "posregister";
	public static final String POSREGISTER_DESC =  "POS Register";
	
	public static final String POSMANAGER_CODE =  "posmanager";
	public static final String POSMANAGER_DESC =  "POS Manager";	
	
	public static final String SELECTION_CODE =  "selection";
	public static final String SELECTION_DESC =  "Selection";	
	
	public static final String YES_CODE =  "YES";
	public static final String YES_DESC =  "Y";	
	
	public static final String NO_CODE =  "NO";
	public static final String NO_DESC =  "N";		
	
	private static final MockLOVHelper _instance = new MockLOVHelper();
	private Map<String, Map<String, String>> allLovs = 
			new HashMap<String, Map<String, String>>();
	
	
	public static MockLOVHelper getInstance() { 
		return _instance; 
	}
	
	public Map<String, String> getLov(String code) throws MooMoneyException {
		return (Map<String, String>) allLovs.get(code);
	}
    
	private MockLOVHelper() {
		Map projectMap = new LinkedHashMap();
		projectMap.put("all","all");
		projectMap.put("personal","personal");
		allLovs.put(LovConstants.LOV_PROJECT, projectMap);
		
	}
	
	public Map<String,String> getLOV(String type) throws MooMoneyException {
		return (Map<String,String>) allLovs.get(type);
	}	
}
