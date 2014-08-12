package com.riviere.moomoney.manager;

import java.util.List;

import com.riviere.moomoney.domain.TransactionCategory;
import com.riviere.moomoney.exception.MooMoneyException;



/**
 * User details data access object interface
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public interface TransactionCategoryManager {
    
	public List<TransactionCategory> getTransactionCategories() throws MooMoneyException;
	public TransactionCategory getTransactionCategoryByKeyword(String phrase) throws MooMoneyException;
    
}
