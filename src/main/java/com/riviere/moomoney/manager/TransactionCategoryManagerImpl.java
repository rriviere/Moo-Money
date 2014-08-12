package com.riviere.moomoney.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riviere.moomoney.dao.TransactionCategoryDao;
import com.riviere.moomoney.domain.TransactionCategory;
import com.riviere.moomoney.exception.MooMoneyException;

@Service
public class TransactionCategoryManagerImpl implements TransactionCategoryManager {

	@Autowired
	private TransactionCategoryDao transactionCategoryDao;

	public List<TransactionCategory> getTransactionCategories() {
		return transactionCategoryDao.getTransactionCategories();
	}

	public TransactionCategory getTransactionCategoryByKeyword(String phrase)
			throws MooMoneyException {
		return transactionCategoryDao.getTransactionCategoryByKeyword(phrase);
	}
	
}
