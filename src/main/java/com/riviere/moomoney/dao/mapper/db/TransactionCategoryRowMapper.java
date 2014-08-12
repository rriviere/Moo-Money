package com.riviere.moomoney.dao.mapper.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.riviere.moomoney.domain.TransactionCategory;

/**
 * EPOS user row mapper
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class TransactionCategoryRowMapper implements RowMapper<TransactionCategory> {
	
	private static final String TRAN_CATEGORY_CODE = "tran_category_code";
	private static final String TRAN_CATEGORY_DESC = "tran_category_desc";
	private static final String TRAN_CATEGORY_SEQ = "tran_category_seq";
	private static final String TRAN_CATEGORY_BUTTON_TYPE = "btn_type";
	

	public TransactionCategory mapRow(ResultSet rs, int rowNum) throws SQLException {

		String tranCategoryCode = rs.getString(TRAN_CATEGORY_CODE);
		TransactionCategory category = new TransactionCategory();
		if (tranCategoryCode!=null){
			category.setTranCategoryCode(tranCategoryCode);
		}
		String tranCategoryDesc = rs.getString(TRAN_CATEGORY_DESC);
		if (tranCategoryDesc!=null){
			category.setTranCategoryDesc(tranCategoryDesc);
		}
		Integer tranCategorySeq = rs.getInt(TRAN_CATEGORY_SEQ);
		if (tranCategorySeq!=null){
			category.setTranCategorySeq(tranCategorySeq);
		}
		String buttonType = rs.getString(TRAN_CATEGORY_BUTTON_TYPE);
		if (buttonType!=null){
			category.setButtonType(buttonType);
		}		
		return category;
	}

}
