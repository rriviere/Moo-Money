package com.riviere.moomoney.dao;

import com.riviere.moomoney.domain.Transaction;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author rriviere
 *
 */
public interface TransactionDao {
	
	 public long saveTransaction(final Transaction transaction) throws MooMoneyException;
}
