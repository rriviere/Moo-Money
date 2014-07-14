package com.riviere.moomoney.dao.lov.mock;

import java.util.Map;

import com.riviere.moomoney.dao.lov.LovDao;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author Richard Riviere
 * @date 02/04/2014
 */
public class MockLovCodeDaoImpl implements LovDao {

	public Map<String,String> getLov(String code) throws MooMoneyException {
		return MockLOVHelper.getInstance().getLOV(code);
	}

	/* (non-Javadoc)
	 * @see com.myer.reporting.dao.lov.LovDao#clearLovCache(java.lang.String)
	 */
	public void clearLovCache(String code) {
		// TODO Auto-generated method stub
		
	}

}
