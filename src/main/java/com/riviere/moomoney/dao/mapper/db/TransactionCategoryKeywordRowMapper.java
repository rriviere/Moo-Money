package com.riviere.moomoney.dao.mapper.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.riviere.moomoney.domain.TransactionCategory;
import com.riviere.moomoney.domain.TransactionCategoryKeyword;

/**
 * EPOS user row mapper
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class TransactionCategoryKeywordRowMapper implements RowMapper<TransactionCategoryKeyword> {
	
	private static final String TRAN_CATEGORY_CODE = "tran_category_code";
	private static final String TRAN_CATEGORY_KEYWORD = "transaction_category_keyword";
	private static final String TRAN_CATEGORY_KEYWORD_ID = "transaction_category_keyword_id";
	private static final String TRAN_CATEGORY_BUTTON_TYPE = "btn_type";
	

	public TransactionCategoryKeyword mapRow(ResultSet rs, int rowNum) throws SQLException {

		String tranCategoryCode = rs.getString(TRAN_CATEGORY_CODE);
		TransactionCategoryKeyword category = new TransactionCategoryKeyword();
		if (tranCategoryCode!=null){
			category.setTranCategoryCode(tranCategoryCode);
		}
		String tranCategoryKeyword = rs.getString(TRAN_CATEGORY_KEYWORD);
		if (tranCategoryKeyword!=null){
			category.setTranCategoryKeyword(tranCategoryKeyword);
		}
		Integer tranCategoryKeywordId = rs.getInt(TRAN_CATEGORY_KEYWORD_ID);
		if (tranCategoryKeywordId!=null){
			category.setTranCategoryKeywordId(tranCategoryKeywordId);
		}
		/*String buttonType = rs.getString(TRAN_CATEGORY_BUTTON_TYPE);
		if (buttonType!=null){
			category.setButtonType(buttonType);
		}	*/	
		return category;
	}

}
