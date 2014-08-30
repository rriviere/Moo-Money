package com.riviere.moomoney.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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
		"SELECT  " +
		"   tc.tran_category_code,  " +
		"   tc.tran_category_desc,  " +
		"   tc.tran_category_seq,  " +
		"   tc.btn_type  " +
		"FROM transaction_category tc, transaction_category_keyword tck  " +
		"WHERE tc.tran_category_code = tck.tran_category_code  " +
		"AND MATCH(tck.transaction_category_keyword) AGAINST (:p_phrase) " +
		"UNION ALL " +
		"SELECT    " +
		"   tc.tran_category_code,  " +
		"   tc.tran_category_desc,  " +
		"   tc.tran_category_seq,  " +
		"   tc.btn_type  " +
		"FROM transaction_category tc " +
		"WHERE tc.tran_category_code='Other' " +
		"AND NOT EXISTS( " +
		"   SELECT 1 " +
		"   FROM transaction_category tc, transaction_category_keyword tck  " +
		"   WHERE tc.tran_category_code = tck.tran_category_code  " +
		"   AND MATCH(tck.transaction_category_keyword) AGAINST (:p_phrase) " +
		") ";

	private static final String SQL_PARAM_PHRASE = "p_phrase";


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

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(SQL_PARAM_PHRASE,phrase);
			
			category = new NamedParameterJdbcTemplate(
					getDataSource()).queryForObject(TRANSACTION_CATEGORY_SELECT_BY_KEYWORD_SQL, 
														(SqlParameterSource)params, 
														new TransactionCategoryRowMapper());			
		}catch(DataAccessException e){
			e.printStackTrace();
			throw new MooMoneyException("getTransactionCategoryByKeyword: " + e.getCause());
		}			
		return category;
	}	
	
}
