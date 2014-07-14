package com.riviere.moomoney.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

/**
 * List of values row mapper
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class LovMapExtractor implements ResultSetExtractor<Object> {

	private static final String LOV_CODE = "code";
	private static final String LOV_VALUE = "value";
	
	public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<String,String> map = new LinkedHashMap<String,String>();
		while (rs.next()) {
			String code = rs.getString(LOV_CODE);
			String value = rs.getString(LOV_VALUE);
			map.put(code, value);
		}
		return map;
	}
}
