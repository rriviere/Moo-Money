package com.riviere.moomoney.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.riviere.moomoney.dao.mapper.TagRowMapper;
import com.riviere.moomoney.domain.Tag;

/**
 * Concrete implementation of user details data access object 
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class TagDaoImpl extends AbstractDao implements TagDao {
	
	/**
	 * select query used to retrieve tag list
	 */
	private static final String TAG_LIST_SQL = 
			" SELECT * FROM etags";
	

	public List<Tag> fetchAllTag() {
		List<Tag> tags = null;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.getDataSource());
		tags = jdbcTemplate.query(TAG_LIST_SQL, new TagRowMapper());
		System.out.println("TAGS:"+ tags);
		return tags;
	}

}
