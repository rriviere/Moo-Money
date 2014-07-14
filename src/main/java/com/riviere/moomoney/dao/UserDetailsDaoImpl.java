package com.riviere.moomoney.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.riviere.moomoney.constants.SecurityConstants;
import com.riviere.moomoney.dao.mapper.UserRowMapper;
import com.riviere.moomoney.domain.MooMoneyUser;

/**
 * Concrete implementation of user details data access object 
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class UserDetailsDaoImpl extends AbstractDao implements UserDetailsDao {
	
	/**
	 * select query used to authenticate users
	 */
	private static final String USER_LOOKUP_SQL = 
			" SELECT u.username,u.password,u.firstname,u.lastname,u.project,u.enabled,ur.role "
			+ " FROM users u, user_roles ur "
			+ " WHERE u.username = ur.username "
			+ " AND u.project = ur.project "
			+ " AND u.username=:p_username "
			+ " AND u.project=:p_project ";
	
	private static final String SQL_PARAM_USERNAME = "p_username"; 
	private static final String SQL_PARAM_PROJECT = "p_project";
	
	
	public MooMoneyUser loadUserByUsername(String input)
			throws UsernameNotFoundException, DataAccessException {
		
		String username=null, project=null;			    
		String[] split = input.split(SecurityConstants.TWO_FACTOR_AUTHENTICTION_DELIM);
		if(split.length == 1){
			username = input;
		}
		else if(split.length == 2){
			username = split[0];
			project = split[1];
		}		
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(SQL_PARAM_USERNAME,username);
		params.addValue(SQL_PARAM_PROJECT, project);
		
		UserRowMapper userRowMapper = new UserRowMapper();		
		MooMoneyUser user = null;
		try {
			user = new NamedParameterJdbcTemplate(
					this.getDataSource()).queryForObject(
							USER_LOOKUP_SQL, 
							(SqlParameterSource)params, 
							userRowMapper);
		}catch(EmptyResultDataAccessException e){
			
		   	throw new UsernameNotFoundException(
		   			"Username:" + username + " does not exist for project:" + project);		    			
		}
		return user;	
	}

}
