package com.riviere.moomoney.manager;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riviere.moomoney.dao.lov.LovDao;
import com.riviere.moomoney.exception.MooMoneyException;
import com.riviere.moomoney.factory.LovDaoFactory;

/**
 * This class is the concrete implementation of the list of values manager.
 * It is referenced in the spring batch jobs xml and qualified as per the annotation.
 * It is an initializing bean.
 * 
 * @author Richard Riviere
 * @date 24/12/2013
 */
@Service("lovManager")
public class LovManagerImpl implements LovManager,InitializingBean {
	
	/**
	 * The list of values data access object factory
	 */
	@Autowired
	private LovDaoFactory lovDaoFactory;
		
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws MooMoneyException {
		getAllLovs();	
	}
	
	/* (non-Javadoc)
	 * @see com.myer.reporting.manager.LovManager#getAllLovs()
	 */
	public void getAllLovs() throws MooMoneyException {
		Map<String,LovDao> lovDaoMap = lovDaoFactory.getLovDaoMap();
		for (Map.Entry<String,LovDao> entry : lovDaoMap.entrySet()){	     
	        String code = (String)entry.getKey();
	        LovDao dao = (LovDao)entry.getValue();
	        dao.clearLovCache(code);
	        dao.getLov(code);
	    }		
	}
	
	/* (non-Javadoc)
	 * @see com.myer.reporting.manager.LovManager#clearAllLovCaches()
	 */
	public void clearAllLovCaches() throws MooMoneyException{
		Map<String,LovDao> lovDaoMap = lovDaoFactory.getLovDaoMap();
		for (Map.Entry<String,LovDao> entry : lovDaoMap.entrySet()){	     
	        String code = (String)entry.getKey();
	        LovDao dao = (LovDao)entry.getValue();
	        dao.clearLovCache(code);
	    }			
		
	}	
	
	/* (non-Javadoc)
	 * @see com.myer.reporting.manager.LovManager#getLov(java.lang.String)
	 */
	public Map<String,String> getLov(String code) throws MooMoneyException {
		Map<String,LovDao> lovDaoMap = lovDaoFactory.getLovDaoMap();
        LovDao dao = (LovDao)lovDaoMap.get(code);
		Map<String,String> lov = dao.getLov(code);		
		return lov;
	}

	
	/**
	 * @return the lovDaoFactory
	 */
	public LovDaoFactory getLovDaoFactory() {
		return lovDaoFactory;
	}

	/**
	 * @param lovDaoFactory the lovDaoFactory to set
	 */
	public void setLovDaoFactory(LovDaoFactory lovDaoFactory) {
		this.lovDaoFactory = lovDaoFactory;
	}
}
