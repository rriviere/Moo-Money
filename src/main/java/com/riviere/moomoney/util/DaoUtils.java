/**
 * 
 */
package com.riviere.moomoney.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import com.riviere.moomoney.constants.DaoConstants;
import com.riviere.moomoney.exception.MooMoneyException;

/**
 * @author rriviere
 *
 */
public class DaoUtils {
	
	public static Date convertStringToAuDate(String auDate){
		Date date = null;
		try {
	    	SimpleDateFormat formatter = new SimpleDateFormat(DaoConstants.DATE_FORMAT_AU);
	    	date = formatter.parse(auDate);
		} catch (ParseException e) {
			throw new MooMoneyException("Could not convert: " + auDate + " to AU date.", e.getCause());
		}
	    return date;
	}
    
	public static List<HashMap<String, String>> parseCsv(
			byte[] bytes,
			String[] columnNames,
			boolean skipFirstRow) throws MooMoneyException {
		List<HashMap<String, String>> records = 
				new ArrayList<HashMap<String, String>>();
		
		ByteArrayInputStream bInput = new ByteArrayInputStream(bytes); 
	    LineNumberReader reader = new LineNumberReader(new InputStreamReader(bInput));
	    try {
		    if (skipFirstRow){
		    	reader.readLine();		    	
		    }
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        StringTokenizer tok = new StringTokenizer(line,",");
		        HashMap<String, String> record = new HashMap<String, String>();
		        int key = 0;
		        while (tok.hasMoreTokens()) {
		            String token = tok.nextToken();
		            String columnName = (String)columnNames[key++];
		            record.put(columnName, token);
		        }
		        records.add(record);
		    }
	    }catch(IOException e){
	    	throw new MooMoneyException("Error parsing bytes: " + e.getCause());
	    }
	    return records;
	} 
}
