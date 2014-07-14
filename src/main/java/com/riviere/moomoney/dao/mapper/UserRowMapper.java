package com.riviere.moomoney.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.riviere.moomoney.domain.MooMoneyUser;

/**
 * EPOS user row mapper
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class UserRowMapper implements RowMapper<MooMoneyUser>{
	
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String FIRST_NAME = "firstname";
	private static final String LAST_NAME = "lastname";
	private static final String PROJECT = "project";
	private static final String ENABLED = "enabled";
	private static final String ROLE = "role";
	private static final Integer ACTIVE = 1;
	
	@Autowired
	private List<String> supportUsers;	

	
	@SuppressWarnings("deprecation")
	public MooMoneyUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		// get user details
		String username = rs.getString(USERNAME);
		String password = rs.getString(PASSWORD);
		String firstname = rs.getString(FIRST_NAME);
		String lastname = rs.getString(LAST_NAME);
		String project = rs.getString(PROJECT);		
		Integer enabled = rs.getInt(ENABLED);
		String role = rs.getString(ROLE);
		if (role!=null){
			role = StringUtils.capitalize(role);
		}
		
		boolean enabledFlag = false;
		if (enabled!=null){
			enabledFlag = (enabled.equals(ACTIVE) ? true: false);
		}
		
		// create authorities
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl(role));			
   				
		// create user
		MooMoneyUser userDetails = 
				new MooMoneyUser(username,password,enabledFlag,true,true,true,authorities,firstname,lastname,project);				

		return userDetails;
	}

}
