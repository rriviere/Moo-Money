package com.riviere.moomoney.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * The spring user class.
 * 
 * @author Richard Riviere
 * @date 13/01/2014
 */
public class MooMoneyUser extends User {
	
	private static final long serialVersionUID = 3155845762713973491L;
	private final String project;
	private final String firstname;
	private final String lastname;

    public MooMoneyUser(
    		String username, 
    		String password, 
    		boolean enabled,
            boolean accountNonExpired, 
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<GrantedAuthority> authorities,
            String firstname,
            String lastname,
            String project) {

    	super(username,password,enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);    	
    	this.firstname = firstname;
    	this.lastname = lastname;
    	this.project = project;
    }

	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
    
}
