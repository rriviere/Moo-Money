package com.riviere.moomoney.controller.ui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ibm.icu.text.SimpleDateFormat;
import com.riviere.moomoney.domain.Transaction;
import com.riviere.moomoney.manager.FilesManager;
import com.riviere.moomoney.manager.TransactionManager;



/**
 * This is the controller for the home screen.
 * All user objects in the session should be cleared when navigating to home.
 * 
 * @author Richard Riviere
 * @date 24/04/2014
 */
@Controller
public class ExpenseListController {
	SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	FilesManager filesManager;
	
	@Autowired
	TransactionManager transactionManager;
	
	@RequestMapping(value = "/expenseList.htm", method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		List<Transaction> transactions = filesManager.getFileContent(4);
		model.addAttribute("transactions", transactions);
		return "parseTransactions";
	}
	
	private Transaction getTransaction(LinkedHashMap<String, Object> map){
		Transaction transaction = new Transaction();
		transaction.setCredit(Double.valueOf(map.get("credit").toString()));
		transaction.setDebit(Double.valueOf(map.get("debit").toString()));
		try {
			transaction.setTranDate(sf.parse(map.get("tranDate").toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.setTranCategoryCode(map.get("tranCategoryCode").toString());
		transaction.setTranDescription(map.get("tranDescription").toString());
		return transaction;
	}
	
	@RequestMapping(value = "/expenseList.htm", method = RequestMethod.POST)
	public @ResponseBody List<Long> saveExpenses(@RequestBody List<Transaction> transactions) {	
		List<Transaction> transactions2 = new ArrayList<Transaction>();
		for(Object object: transactions){
			LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) object;
			 transactions2.add(getTransaction(map));
		}
		
		List<Long> createdTransactionIds = transactionManager.saveTransactions(transactions2);
		return createdTransactionIds;
	}	
	
	@RequestMapping(value = "/saveReceipt.htm", method = RequestMethod.POST)
	public @ResponseBody Long updateReceipt(@RequestParam(value = "tranId") String tranId, @RequestParam(value = "fileId") String fileID) {	
		Long transactionId = Long.valueOf(tranId) ;
		Long fileId=  Long.valueOf(fileID);
		Long updatedTransactionId = transactionManager.updateTransactionReceipt(transactionId, fileId);
		return updatedTransactionId;
	}	
}
