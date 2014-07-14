package com.riviere.moomoney.manager;

import java.util.Map;

import com.riviere.moomoney.exception.MooMoneyException;

/**
 * List of values service interface
 * 
 * @author Richard Riviere
 * @date 24/03/2014
 */
public interface LovManager {
	
	/**
	 * Retrieve all list of value maps. Used to initialize or refresh the cache.
	 * 
	 * @throws MooMoneyException
	 */
	public void getAllLovs() throws MooMoneyException;
	
	/**
	 * Clear all list of value caches
	 * 
	 * @throws MooMoneyException
	 */
	public void clearAllLovCaches() throws MooMoneyException;
	
	/**
	 * Retrieve list of values for the given code.
	 * 
	 * @param code
	 * @return
	 * @throws MooMoneyException
	 */	
	public Map<String,String> getLov(String code) throws MooMoneyException;	
	
}
