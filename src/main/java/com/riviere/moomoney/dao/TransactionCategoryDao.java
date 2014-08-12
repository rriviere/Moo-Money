package com.riviere.moomoney.dao;

import java.util.List;

import com.riviere.moomoney.domain.TransactionCategory;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author rriviere
 *
 */
public interface TransactionCategoryDao {
	public List<TransactionCategory> getTransactionCategories()throws MooMoneyException;
	public TransactionCategory getTransactionCategoryByKeyword(String phrase)throws MooMoneyException;
}
