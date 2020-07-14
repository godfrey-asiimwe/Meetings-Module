package com.planetsystems.core.meeting.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

public class PropertyPlaceHolderConfigurer extends PropertyPlaceholderConfigurer {
	private String classPathPropertiesFilename = "persistence-dbc.properties";
	private String environmentVariable = "persistence-dbc";
	
	private Properties applicationSettings;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.core.io.support.PropertiesLoaderSupport#loadProperties
	 *      (java.util.Properties)
	 */
	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
		loadContextBasedProperties(props);
		this.applicationSettings = props;
	}

	/**
	 * loads the application properties
	 * 
	 * @param props
	 * @throws IOException
	 *             thrown when the application properties file is not found
	 */
	private void loadContextBasedProperties(Properties props) throws IOException {
		// only attempt to load new properties if they were not found in the
		// default location
		if (props.isEmpty()) {
			loadFromClassPath(props);
			loadFromEnvironmentVariable(props);
		}
	}

	/**
	 * loads the application properties from the classpath
	 * 
	 * @param props
	 * @throws IOException
	 *             thrown if the properties file is not found
	 */
	private void loadFromClassPath(Properties props) throws IOException {
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(getClassPathPropertiesFilename());
		if (stream != null) {
			props.load(stream);
			super.setProperties(props);
		}
	}

	/**
	 * loads the application properties from a path specified in the environment
	 * variable
	 * 
	 * @param props
	 * @throws IOException
	 *             thrown if the path specified in the environment variable is
	 *             not found
	 */
	private void loadFromEnvironmentVariable(Properties props) throws IOException {
		File settingsFile = getSettingsFile(environmentVariable);
		if (settingsFile != null) {
			log.info("Attempting to load properties from: " + settingsFile.getAbsolutePath());
			setFileSystemLocation(settingsFile);
			super.loadProperties(props);
		}
	}

	/**
	 * sets the location of the properties file from the file system
	 * 
	 * @param fileLocation
	 */
	private void setFileSystemLocation(File fileLocation) {
		if (fileLocation != null) {
			FileSystemResource resource = new FileSystemResource(fileLocation);
			setLocation(resource);
		}
	}

	/**
	 * @param environVariable
	 * @return
	 */
	private File getSettingsFile(String environVariable) {
		String systemVariable = System.getenv(environVariable);
		if (systemVariable == null) {
			systemVariable = System.getProperty(environVariable);
		}

		File settingsFile = null;
		if (systemVariable != null) {
			settingsFile = new File(systemVariable);
		} else {
			log.warn("Unable to find environment or system variable: " + environVariable);
		}

		return settingsFile;
	}

	/**
	 * gets the application properties
	 * 
	 * @return the systemProperties
	 */
	public Properties getApplicationProperties() {
		return applicationSettings;
	}

	/**
	 * sets the application properties
	 * 
	 * @param appProps
	 *            the applicationSettings to set
	 */
	public void setApplicationProperties(Properties appProps) {
		this.applicationSettings = appProps;
	}

	/**
	 * gets the application properties file name
	 * 
	 * @return the propertiesFilename
	 */
	public String getPropertiesFilename() {
		return classPathPropertiesFilename;
	}

	/**
	 * sets the application properties file name
	 * 
	 * @param propertiesFilename
	 *            the propertiesFilename to set
	 */
	public void setPropertiesFilename(String propertiesFilename) {
		this.classPathPropertiesFilename = propertiesFilename;
	}

	/**
	 * gets the properties filename that will be scanned from the classpath
	 * 
	 * @return the classPathPropertiesFilename
	 */
	public String getClassPathPropertiesFilename() {
		return classPathPropertiesFilename;
	}

	/**
	 * sets the properties file name that will be scanned from the classpath
	 * 
	 * @param classPathPropertiesFilename
	 *            the classPathPropertiesFilename to set
	 */
	public void setClassPathPropertiesFilename(String classPathPropertiesFilename) {
		this.classPathPropertiesFilename = classPathPropertiesFilename;
	}

	/**
	 * gets the environment variable from which to get the path to the
	 * properties file of the application
	 * 
	 * @return the environmentVariable
	 */
	public String getEnvironmentVariable() {
		return environmentVariable;
	}

	/**
	 * sets the environment variable from which to get the path to the
	 * properties file of the application
	 * 
	 * @param environmentVariable
	 *            the environmentVariable to set
	 */
	public void setEnvironmentVariable(String environmentVariable) {
		this.environmentVariable = environmentVariable;
	}
}
