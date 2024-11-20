package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalPropertiesLoader {

	private final Properties prop;
	private static final String DEFAULT_PROPERTIES = "src/test/resources/global.properties";

	// GlobalProperties Singleton
	// Private constructor to prevent the class is instantiated outside this class
	private GlobalPropertiesLoader() {

		// This code part loads global.properties file
		prop = new Properties();
		try {
			FileInputStream stream = new FileInputStream(DEFAULT_PROPERTIES);
			prop.load(stream);
		} catch (IOException e) {
			FWLogger.error("Unable to read property file " + DEFAULT_PROPERTIES + e);
		}

		// Overrides global.properties values with Maven System Properties
		for (String key : prop.stringPropertyNames()) {
			if (System.getProperties().containsKey(key)) {
				prop.setProperty(key, System.getProperty(key));
			}
		}
	}

	public String getBrowserName() {
		return prop.getProperty(Keywords.BROWSER.toString());
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}
