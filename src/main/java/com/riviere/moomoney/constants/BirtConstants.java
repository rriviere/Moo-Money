package com.riviere.moomoney.constants;


/**
 * This class holds constants used in BIRT reports
 * 
 * @author Richard Riviere
 * @date 02/06/2014
 */
public class BirtConstants {
	
	// birt servlet paramters
	public static final String OUTPUT_ARG = "/output";
	public static final String REPORT_PARAM = "?__report=";
	public static final String REPORT_ROOT = "resources/reports/";
	public static final String REPORT_SUFFIX = ".rptdesign";
	public static final String FORMAT_PDF = "&__format=pdf";
	public static final String FORMAT_HTML = "&__format=html";
	public static final String PARAM_PREFIX = "bp_";
	
	// birt report paramters
	public static final String PARAM_POS_STORE = PARAM_PREFIX + "posStore";
	public static final String PARAM_POS_ID = PARAM_PREFIX + "posId";
	public static final String PARAM_POS_DISPLAY_NAME = PARAM_PREFIX + "posDisplayName";
	public static final String PARAM_WEEK_ENDING = PARAM_PREFIX + "weekEnding";
	
	public static final String PARAM_SELECTED_BUSINESS_DATE = PARAM_PREFIX + "selectedBusinessDate";
	public static final String PARAM_RANGE_ONE_BUSINESS_DATE = PARAM_PREFIX + "rangeOneBusinessDate";
	public static final String PARAM_RANGE_TWO_BUSINESS_DATE = PARAM_PREFIX + "rangeTwoBusinessDate";
	public static final String PARAM_WEEK_ENDING_BUSINESS_DATE = PARAM_PREFIX + "weekEndingBusinessDate";
	public static final String PARAM_LOGICAL_TERMINAL_ID = PARAM_PREFIX + "logicalTerminalId";
	public static final String PARAM_TEAM_MEMBER_ID = PARAM_PREFIX + "teamMemberId";
	public static final String PARAM_GROUPING_TYPE = PARAM_PREFIX + "groupType";
	public static final String PARAM_SORTING_TYPE = PARAM_PREFIX + "sortType";
	public static final String PARAM_CONCESSION_FILTER = PARAM_PREFIX + "excludeConcessionInd";
	
	// birt report paramter DEFAULTS
	public static final String PARAM_NO = "N";
	public static final String LOGICAL_TERMINAL_ID_PREFIX = "POS.";
	public static final String LOGICAL_TERMINAL_ID_SUFFIX = ".1";
	public static final String TEAM_MEMBER_ID_PREFIX = "0";
	

}
