package com.riviere.moomoney.spring;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * A spring application initializer implementation
 * 
 * @author Richard Riviere
 * @date 04/02/2014
 */
public class AppInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	
	public static final String ENV_PROPERTY_FILE = "classpath:env.properties";
	public static final String SPRING_PROFILE_PROPERTY_NAME = "spring.profiles.active";
	
	private static final Log logger = 
			LogFactory.getLog(AppInitializer.class);
	
	
    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationContextInitializer#initialize(org.springframework.context.ConfigurableApplicationContext)
     */
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        try {
        	ResourcePropertySource env = new ResourcePropertySource(ENV_PROPERTY_FILE);
        	String profile = (String)env.getProperty(SPRING_PROFILE_PROPERTY_NAME);      	
            environment.getPropertySources().addFirst(env);
            logger.info("env.properties loaded with " + profile + " profile.");
        } catch (IOException e) {
            // it's ok if the file is not there. we will just log that info.
        	logger.info("didn't find env.properties in classpath so not loading it in the AppContextInitialized");
        }
    }

}