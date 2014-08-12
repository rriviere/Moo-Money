package com.riviere.moomoney.manager;

import java.util.List;

import com.riviere.moomoney.domain.Transaction;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author rriviere
 *
 */
public interface TransactionManager {

	public long saveTransaction(final Transaction transaction) throws MooMoneyException;
	public List<Long> saveTransactions(final List<Transaction> transactions)throws MooMoneyException;
}
