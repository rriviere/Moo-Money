package com.riviere.moomoney.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * List of values row mapper
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class LovRowMapper implements RowMapper<Map<String,String>> {

	private static final String LOV_CODE = "code";
	private static final String LOV_VALUE = "value";
	
	public Map<String,String> mapRow(ResultSet rs, int rowNum) throws SQLException {
		Map<String,String> map = new LinkedHashMap<String,String>();
		String code = rs.getString(LOV_CODE);
		String value = rs.getString(LOV_VALUE);
		map.put(code, value);
		return map;
	}
}

