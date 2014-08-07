package com.riviere.moomoney.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.riviere.moomoney.domain.MooMoneyUser;
import com.riviere.moomoney.domain.Tag;

/**
 * Tag details data access object interface
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public interface TagDao  {
	
		public List<Tag> fetchAllTag();
	
}
