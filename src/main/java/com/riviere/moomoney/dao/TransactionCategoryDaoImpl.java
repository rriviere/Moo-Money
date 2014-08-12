package com.riviere.moomoney.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.riviere.moomoney.dao.mapper.db.TransactionCategoryRowMapper;
import com.riviere.moomoney.domain.TransactionCategory;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author rriviere
 *
 */
public class TransactionCategoryDaoImpl extends AbstractDao implements TransactionCategoryDao {
	
	/**
	 * select query used to retrieve tag list
	 */
	private static final String TRANSACTION_CATEGORY_SELECT_SQL = 
		"SELECT  " +
		"   tran_category_code,  " +
		"   tran_category_desc,  " +
		"   tran_category_seq,  " +
		"   btn_type " +
		"FROM transaction_category ";
	
	private static final String TRANSACTION_CATEGORY_SELECT_BY_KEYWORD_SQL = 
		"    SELECT   " +
		"       tck.transaction_category_keyword_id, " +
		"       tck.transaction_category_keyword, " +
		"       tck.tran_category_code " +
		"    FROM transaction_category tc, transaction_category_keyword tck " +
		"    WHERE tc.tran_category_code = tck.tran_category_code " +
		"    AND tck.transaction_category_keyword='Bupa' ";

	public List<TransactionCategory> getTransactionCategories() throws MooMoneyException {
		List<TransactionCategory> categories = null;
		try {
			categories = 
					getJdbcTemplate()
					.query(TRANSACTION_CATEGORY_SELECT_SQL, new TransactionCategoryRowMapper());				
		}catch(DataAccessException e){
			throw new MooMoneyException("getTransactionCategories: " + e.getCause());
		}
		return categories;
	}
	
	public TransactionCategory getTransactionCategoryByKeyword(String phrase) throws MooMoneyException {
		TransactionCategory category = null;
		try {
			category = 
				getJdbcTemplate()
				.queryForObject(
						TRANSACTION_CATEGORY_SELECT_BY_KEYWORD_SQL
						, new Object[]{phrase},
						new TransactionCategoryRowMapper());
			
		}catch(DataAccessException e){
			throw new MooMoneyException("getTransactionCategoryByKeyword: " + e.getCause());
		}			
		return category;
	}	
	
}
