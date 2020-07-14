package com.planetsystems.core.meeting.util;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * this class provides access to the spring application context for classes that
 * are outside the spring application context.
 * 
 * @author
 * 
 */
@Component("applicationContextProvider")
public class ApplicationContextProvider implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	private static Properties applicationProperties;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextProvider.applicationContext = applicationContext;
		PropertyPlaceHolderConfigurer propertyConfigurer = getBean(PropertyPlaceHolderConfigurer.class);
		if (propertyConfigurer != null)
			applicationProperties = propertyConfigurer.getApplicationProperties();
	}

	/**
	 * gets the application context
	 * 
	 * @return
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Return the bean instance that uniquely matches the given object type, if
	 * any.
	 * 
	 * @param type
	 *            type the bean must match; can be an interface or superclass.
	 *            null is disallowed.
	 * @return an instance of the single bean matching the required type
	 */
	public static <T> T getBean(Class<T> type) {
		return ApplicationContextProvider.applicationContext.getBean(type);
	}

	/**
	 * gets the value of the property with the given name configured in the
	 * application settings.
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String getProperty(String propertyName) {
		if (applicationProperties != null) {
			return applicationProperties.getProperty(propertyName);
		}

		return "";
	}

	/**
	 * 
	 * @param propertyName
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String propertyName, String defaultValue) {
		if (applicationProperties != null) {
			return applicationProperties.getProperty(propertyName, defaultValue);
		}

		return defaultValue;
	}
}
