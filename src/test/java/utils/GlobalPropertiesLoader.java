package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GlobalPropertiesLoader {

	private final Properties prop;

	// GlobalProperties Singleton
	// Private constructor to prevent the class is instantiated outside this class
	private GlobalPropertiesLoader() {

		prop = new Properties();

		try {
			FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load global properties file, please check global.properties.");
		}
	}

	public String getBrowserName() {
		return prop.getProperty(Keywords.BROWSER.toString());
	}

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}
