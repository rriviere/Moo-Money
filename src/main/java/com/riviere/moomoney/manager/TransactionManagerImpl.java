package com.riviere.moomoney.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riviere.moomoney.dao.TransactionDao;
import com.riviere.moomoney.domain.Transaction;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author rriviere
 *
 */
@Service
public class TransactionManagerImpl implements TransactionManager {
	
	@Autowired
	TransactionDao transactionDao;
	
	public long saveTransaction(final Transaction transaction) throws MooMoneyException {
		return transactionDao.saveTransaction(transaction);
	}

	public List<Long> saveTransactions(final List<Transaction> transactions)
			throws MooMoneyException {
		List<Long> createdTransactionIds = new ArrayList<Long>();
		for(Transaction transaction : transactions){
			createdTransactionIds.add(saveTransaction(transaction));
		}
		return createdTransactionIds;
	}
}
