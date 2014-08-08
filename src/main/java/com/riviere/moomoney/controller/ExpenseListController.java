package com.riviere.moomoney.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.riviere.moomoney.domain.FinancialTransaction;
import com.riviere.moomoney.manager.FilesManager;


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
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(
			ModelMap model) {
		
		List<FinancialTransaction> transactions = filesManager.getFileContent(51);
		model.addAttribute("transactions", transactions);
		return "expenses";
	}
}
