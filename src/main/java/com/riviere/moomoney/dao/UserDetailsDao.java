package com.riviere.moomoney.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.riviere.moomoney.domain.MooMoneyUser;

/**
 * User details data access object interface
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public interface UserDetailsDao extends UserDetailsService {
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	public MooMoneyUser loadUserByUsername(String userId)
			throws UsernameNotFoundException, DataAccessException;
	
}
