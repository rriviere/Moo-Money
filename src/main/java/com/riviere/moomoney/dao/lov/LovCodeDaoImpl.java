package com.riviere.moomoney.dao.lov;

import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.riviere.moomoney.dao.AbstractDao;
import com.riviere.moomoney.dao.mapper.db.LovMapExtractor;

/**
 * Concrete implementation of list of values data access object
 * 
 * @author Richard Riviere
 * @date 24/12/2013
 */
public class LovCodeDaoImpl extends AbstractDao implements LovDao {

	/**
	 * Sql to retrieve code type list of values
	 */
	private static final String CODE_LOV_SQL = 
		" SELECT  " +
		"   code, " +
		"   code_desc value" +
		" FROM repman.code_detail  " +
		" WHERE code_type=?  " +
		" ORDER BY code_seq ";

	/* (non-Javadoc)
	 * @see com.myer.reporting.dao.LovDao#getLov()
	 */
	@Cacheable(value = "lovs", key = "#code")
	public Map<String, String> getLov(String code) {	    
		
		Map<String, String> codes = 				
			(Map<String, String>) getJdbcTemplate()
				.query(
					CODE_LOV_SQL
					,new Object[]{code} 
					,new LovMapExtractor());	
		
		return codes;
	}
	
	@CacheEvict(value = "lovs", allEntries=true)
	public void clearLovCache(String code) {
		// Intentionally blank
	}
}
