package com.riviere.moomoney.controller.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping(value = "/expenseList.htm")
public class ExpenseListController {
	
	@Autowired
	FilesManager filesManager;
	
	@Autowired
	TransactionManager transactionManager;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(ModelMap model) {
		List<Transaction> transactions = filesManager.getFileContent(51);
		model.addAttribute("transactions", transactions);
		return "parseTransactions";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody List<Long> saveExpenses(@RequestBody List<Transaction> transactions) {		
		List<Long> createdTransactionIds = transactionManager.saveTransactions(transactions);
		return createdTransactionIds;
	}	
}
