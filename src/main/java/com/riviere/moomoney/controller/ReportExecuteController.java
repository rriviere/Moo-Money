package com.riviere.moomoney.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller that feeds into a report. Not used. Keeping this as good reference for
 * alternative way of executing a report.
 *
 * @author Richard Riviere
 */
@Controller
@RequestMapping(value="/reports")
public class ReportExecuteController{

	/**
	 * Not used. Keeping this as good reference for alternative way of executing a report.
	 * 
	 * @param birtUrl
	 * @param viewName
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/show.htm", method = RequestMethod.GET)
    public ModelAndView show (
    		@RequestParam String birtUrl,
    		@RequestParam String viewName,
    		HttpServletRequest request,
    		HttpServletResponse response) {
 	
    	StringBuffer reportUrlBuilder = new StringBuffer(request.getContextPath());
    	StringBuffer reportPdfUrlBuilder = new StringBuffer(request.getContextPath());
    	
    	reportUrlBuilder.append(birtUrl +"&__format=html");
    	reportPdfUrlBuilder.append(birtUrl+"&__format=pdf");
    	
		String encodedReportUrl = response.encodeURL(reportUrlBuilder.toString());
		String encodedReportPDFUrl = response.encodeURL(reportPdfUrlBuilder.toString());
		
		Map<String, Object> modelData = new HashMap<String, Object>();
		modelData.put("reportUrl", encodedReportUrl);
		modelData.put("reportPDFUrl", encodedReportPDFUrl);
	
    	return new ModelAndView(viewName,modelData);    	
    } 
}
