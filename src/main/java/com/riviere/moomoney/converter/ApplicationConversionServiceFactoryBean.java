package com.riviere.moomoney.converter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

import com.riviere.moomoney.manager.LovManager;

/**
 * This is the application conversion service to be autowired into a number of other
 * controllers and spring beans.
 * 
 * @author Richard Riviere
 * @date 12/02/2014
 */
@Component("applicationConversionService")
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	/**
	 * The date format passed into birt reports
	 */
	@Value("#{appProperties['birt.date.format']}")
	public String birtDateFormat = null;
	
	/**
	 * the date format for front end screens
	 */
	@Value("#{appProperties['date.format']}")
	public String dateFormat = null;	
	
	/**
	 * Sql date format for db queries 
	 */
	@Value("#{appProperties['sql.datetime.format']}")
	public String sqlDateFormat = null;	
	
	/**
	 * The list of values manager
	 */
	@Autowired
	private LovManager lovManager; 
	
	/* (non-Javadoc)
	 * @see org.springframework.format.support.FormattingConversionServiceFactoryBean#installFormatters(org.springframework.format.FormatterRegistry)
	 */
	@Override
    protected void installFormatters(FormatterRegistry registry) {
        super.installFormatters(registry);
        registry.addConverter(getDateToBirtParamterStringConverter());
    }
	
    /**
     * Convert to a string in birt date format
     * 
     * @return
     */
    public Converter<Date, String> getDateToBirtParamterStringConverter() {
        return new Converter<Date, String>() {
            public String convert(Date source) {
                SimpleDateFormat sdf = new SimpleDateFormat(birtDateFormat);
                String dateStr = null;
                if (source != null){
                	dateStr = sdf.format(source);
                }                
                return dateStr;
            }
        };
    }
    

    /**
     * Convert to a string in sql date format
     * 
     * @return
     */
    public Converter<Date, String> getDateToSqlDateStringConverter() {
        return new Converter<Date, String>() {
            public String convert(Date source) {
                SimpleDateFormat sdf = new SimpleDateFormat(sqlDateFormat); 
                String dateStr = null;
                if (source != null){
                	dateStr = sdf.format(source);
                }
                return dateStr;
            }
        };        
    } 
    
    /**
     * Convert timestamp to date with 0 nanos
     * @return
     */
    public Converter<Timestamp, Date> getDbTimestampToDateWithoutNanoConverter() {
        return new Converter<Timestamp, Date>() {
            public Date convert(Timestamp source) {
            	source.setNanos(0);
                return new Date(source.getTime());
            }
        };
    } 
    
   
        
    
}
