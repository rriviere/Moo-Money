package com.riviere.moomoney.dao.mapper.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.riviere.moomoney.domain.Tag;

/**
 * EPOS user row mapper
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class TagRowMapper implements RowMapper<Tag>{
	
	private static final String ID = "id";
	private static final String ITEM_CODE = "item_code";
	private static final String ITEM_DESCRIPTION = "item_description";
	private static final String BUTTON_TYPE = "btn_type";
	
	

	@SuppressWarnings("deprecation")
	public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		// get Tag details
		long id = rs.getLong(ID);
		String itemCode = rs.getString(ITEM_CODE);
		String itemDescription = rs.getString(ITEM_DESCRIPTION);
		String buttonType = rs.getString(BUTTON_TYPE);

		// create Tag
		Tag tag = 
				new Tag(id,itemCode,itemDescription,buttonType);				

		return tag;
	}

}
