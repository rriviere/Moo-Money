package com.riviere.moomoney.factory;

import java.util.HashMap;
import java.util.Map;

import com.riviere.moomoney.dao.lov.LovDao;

/**
 * A factory holding all the list of values daos
 * 
 * @author Richard Riviere
 * @date 08/05/2014
 */
public class LovDaoFactory {
	
	/**
	 * Map holding dao's
	 */
	public Map<String, LovDao> lovDaoMap = new HashMap<String,LovDao>();
	
	/**
	 * Get list of values dao's
	 * 
	 * @param lovCode
	 * @return
	 */
	public LovDao getLovDao(String lovCode){
		LovDao lovDao = lovDaoMap.get(lovCode);
		return lovDao;
	}

	/**
	 * @return the lovDaoMap
	 */
	public Map<String, LovDao> getLovDaoMap() {
		return lovDaoMap;
	}

	/**
	 * @param lovDaoMap the lovDaoMap to set
	 */
	public void setLovDaoMap(Map<String, LovDao> lovDaoMap) {
		this.lovDaoMap = lovDaoMap;
	}
	
}
