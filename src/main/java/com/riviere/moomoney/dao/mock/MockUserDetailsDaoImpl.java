package com.riviere.moomoney.dao.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.riviere.moomoney.constants.SecurityConstants;
import com.riviere.moomoney.dao.UserDetailsDao;
import com.riviere.moomoney.domain.MooMoneyUser;

/**
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class MockUserDetailsDaoImpl implements UserDetailsDao {
	
	private Map<String, String> usernamePasswordMap = null;
	private Map<String, String> usernameRoleMap = null;

	public MooMoneyUser loadUserByUsername(String input)
			throws UsernameNotFoundException, DataAccessException{
		MooMoneyUser user = null;
		
		String username=null, project=null;			    
		String[] split = input.split(SecurityConstants.TWO_FACTOR_AUTHENTICTION_DELIM);
		if(split.length == 1){
			username = input;
		}
		else if(split.length == 2){
			username = split[0];
			project = split[1];
		}	
		
    	String password = usernamePasswordMap.get(username);
    	if (password!=null){    	    		    		
    		String role = usernameRoleMap.get(username);
    		//GrantedAuthority[] authorities = {new GrantedAuthorityImpl(role)};
    		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    		authorities.add(new GrantedAuthorityImpl(role));
    		String firstname = StringUtils.capitalize(username);
    		String lastname = StringUtils.capitalize(password);
    		boolean enabledFlag = true;
    		new MooMoneyUser(username,password,enabledFlag,true,true,true,authorities,firstname,lastname,project);						    				
    	}else {
    		throw new UsernameNotFoundException("Could not find: " + username);
        }	
    	return user; 
	}		

	
    /**
	 * @return the usernamePasswordMap
	 */
	public Map<String, String> getUsernamePasswordMap() {
		return usernamePasswordMap;
	}

	/**
	 * @param usernamePasswordMap the usernamePasswordMap to set
	 */
	public void setUsernamePasswordMap(Map<String, String> usernamePasswordMap) {
		this.usernamePasswordMap = usernamePasswordMap;
	}

	/**
	 * @return the usernameRoleMap
	 */
	public Map<String, String> getUsernameRoleMap() {
		return usernameRoleMap;
	}

	/**
	 * @param usernameRoleMap the usernameRoleMap to set
	 */
	public void setUsernameRoleMap(Map<String, String> usernameRoleMap) {
		this.usernameRoleMap = usernameRoleMap;
	}
	
}
