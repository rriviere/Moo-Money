package com.riviere.moomoney.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.riviere.moomoney.domain.TransactionCategory;
import com.riviere.moomoney.manager.TransactionCategoryManager;


@Controller
@RequestMapping("/service")
public class TransactionCategoryController extends AbstractServiceController {
	
	@Autowired
	TransactionCategoryManager transactionCategoryManager;

	@RequestMapping(value="/transactionCategories.json", method = RequestMethod.GET)
	public @ResponseBody List<TransactionCategory> getTransactionCategories() {
		List<TransactionCategory> transactionCategories = transactionCategoryManager.getTransactionCategories();
		return transactionCategories;
	}
	
	@RequestMapping(value="/transactionCategoryByKeyword.json", method = RequestMethod.GET)
	public @ResponseBody TransactionCategory getTransactionCategoryByKeyword (
			@RequestParam(value = "phrase") String phrase) {
		TransactionCategory transactionCategory = transactionCategoryManager.getTransactionCategoryByKeyword(phrase);
		return transactionCategory;
	}		
}
