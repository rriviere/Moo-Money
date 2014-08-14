package com.riviere.moomoney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.riviere.moomoney.domain.Transaction;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author rriviere
 *
 */
public class TransactionDaoImpl extends AbstractDao implements TransactionDao {
	
	private static final String SAVE_TRANSACTION_SQL = 
			" INSERT INTO transaction ( transaction_date, description, debit, credit, tran_category_code)"
			+ "	VALUES (?, ?, ?, ?, ?) ";
	
	private static final String UPDATE_TRANSACTION_RECEIPT_SQL = 
			" UPDATE transaction SET receipt=? WHERE transaction_id=?";
	
	private static final String TRANSACTION_ID = "TRANSACTION_ID";
	
    public long saveTransaction(final Transaction transaction) throws MooMoneyException {
    	long key = -1;
    	synchronized(this) {    		
    		KeyHolder keyHolder = new GeneratedKeyHolder();
    		getJdbcTemplate().update(new PreparedStatementCreator() {
 
    			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    				PreparedStatement statement = con.prepareStatement(
    						SAVE_TRANSACTION_SQL, 
    						new String[]{TRANSACTION_ID});
    				
    				Date tranDate = transaction.getTranDate();
                    statement.setDate(1, new java.sql.Date(tranDate.getTime()));
                    
                    String tranDescription = transaction.getTranDescription();
                    statement.setString(2, tranDescription);
                    
                    Double debit = transaction.getDebit();
                    statement.setDouble(3, debit);

                    Double credit = transaction.getCredit();
                    statement.setDouble(4, credit);
                    
                    String tranCategoryCode = transaction.getTranCategoryCode();
                    statement.setString(5, tranCategoryCode);
                    
                    return statement;
                } 
            }, keyHolder);
    		if (keyHolder != null){
    			key = keyHolder.getKey().longValue();
    		}
        }
    	return key;
    }	
    
    public Long updateTransactionReceipt(long transactionId, long fileId)
    		throws MooMoneyException {
    	try {
            getJdbcTemplate().update(UPDATE_TRANSACTION_RECEIPT_SQL, new Object[] {fileId, transactionId});
        } catch (Exception ex) {
        	ex.printStackTrace();
            throw new MooMoneyException("updateTransactionReceipt Exception", ex);
        }
    	return transactionId;
    }
}
