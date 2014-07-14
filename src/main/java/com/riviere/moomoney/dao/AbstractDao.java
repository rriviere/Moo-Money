package com.riviere.moomoney.dao;

import java.util.Date;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.riviere.moomoney.exception.MooMoneyOptimisticLockingException;

/**
 * An abstract data access object for all other data access object's to extend off.
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public abstract class AbstractDao extends JdbcDaoSupport{
	
	/**
	 * Do an optimistic locking check with a db date vs an optimistic 
	 * locking date against the object.
	 * 
	 * @param dbLastModified A fresh copy of the last modified date from the database
	 * @param optimisticLockingDate an optimistic locking date against the object
	 * @param optimisticLockingMessage an optimistic locking error
	 * @throws MooMoneyOptimisticLockingException
	 */
	protected void optimisticLockingCheck (
			Date dbLastModified, 
			Date optimisticLockingDate,
			String optimisticLockingMessage) 
			throws MooMoneyOptimisticLockingException{
		
		if (dbLastModified.after(optimisticLockingDate)){
			throw new MooMoneyOptimisticLockingException(optimisticLockingMessage);
		}		
	}

}
