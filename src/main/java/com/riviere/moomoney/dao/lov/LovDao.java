package com.riviere.moomoney.dao.lov;

import java.util.Map;

import com.riviere.moomoney.exception.MooMoneyException;

/**
 * List of values data access object interface
 * 
 * @author Richard Riviere
 * @date 24/12/2013
 */
public interface LovDao {
	
	
	/**
	 * Retrieve list of values for the given code.
	 * 
	 * @param code
	 * @return
	 * @throws MooMoneyException
	 */
	public Map<String,String> getLov(String code) throws MooMoneyException;
	
	
	/**
	 * Clear all the values in the cache for the given code 
	 */
	public void clearLovCache(String code);
	
}
