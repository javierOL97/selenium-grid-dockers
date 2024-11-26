package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GlobalPropertiesLoader {

	private final Properties prop;
	//private static final String FILE_PROPERTIES_PATH = "src/main/resources/global.properties";
	private static final String FILE_PROPERTIES_PATH = "config/global.properties";
	private static final String JAR_PROPERTIES_PATH = "/global.properties";

	// GlobalProperties Singleton
	// Private constructor to prevent the class is instantiated outside this class
	private GlobalPropertiesLoader() {

		// This constructor loads global.properties file. If we are executing our
		// framework from eclipse or maven
		// it will use FILE_PROPERTIES_PATH. If we execute our framework with our JAR
		// file it will use
		// JAR_PROPERTIES_PATH
		prop = new Properties();
		try {
			InputStream stream;
			File file = new File(FILE_PROPERTIES_PATH);

			if (file.exists()) {
				stream = new FileInputStream(file);
				FWLogger.info("Loading global properties from file system: " + FILE_PROPERTIES_PATH);
			} else {
				stream = getClass().getResourceAsStream(JAR_PROPERTIES_PATH);
				if (stream == null) {
					throw new IOException("Property file '" + JAR_PROPERTIES_PATH + "' not found in the classpath");
				}
				FWLogger.info("Loading global properties from JAR executable: " + JAR_PROPERTIES_PATH);
			}

			prop.load(stream);
			stream.close();

		} catch (IOException e) {
			FWLogger.error("Unable to read property file: " + JAR_PROPERTIES_PATH + " -> " + e.getMessage());
		}

		// Override global.properties values with Maven System Properties
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
