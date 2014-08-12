package com.riviere.moomoney.dao.mapper.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import com.riviere.moomoney.domain.Transaction;
import com.riviere.moomoney.util.DaoUtils;

/**
 * @author rriviere
 *
 */
@Component
public class CsvTransactionMapper implements CsvRowMapper<List<Transaction>> {
	
	private static final String TRAN_DATE = "tranDate";
	private static final String TRAN_DESCRIPTION = "tranDescription";
	private static final String DEBIT = "debit";
	private static final String CREDIT = "credit";
	
	public static final String[] columnNames = { TRAN_DATE, TRAN_DESCRIPTION, DEBIT, CREDIT};
	
    public List<Transaction> mapRow(List<HashMap<String, String>> records){
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		for (HashMap<String, String> record : records) {
			Transaction transaction = new Transaction();
			String tranDate = (String)record.get(TRAN_DATE);
			if (tranDate!=null){
				transaction.setTranDate(DaoUtils.convertStringToAuDate(tranDate));
			}
			String tranDescription = (String)record.get(TRAN_DESCRIPTION);
			if (tranDescription!=null){
				transaction.setTranDescription(tranDescription);				
			}
			String debit = (String)record.get(DEBIT);
			if (debit!=null){
				transaction.setDebit(Double.valueOf(debit));
			}
			String credit = (String)record.get(CREDIT);
			if (credit!=null){
				transaction.setCredit(Double.valueOf(credit));	
			}
			transactions.add(transaction);
		}
		return transactions;
    }	
}
