package browserManager;

import singletonManager.SingletonFactory;
import utils.GlobalPropertiesLoader;

public class DriverFactory {

	private IBrowserManager chromeManager;
	private IBrowserManager firefoxManager;

	private DriverFactory() {

	}

	public IBrowserManager getBrowserManager() {

		switch (SingletonFactory.getSingletonInstance(GlobalPropertiesLoader.class).getBrowserName()) {
		case "chrome": {
			if (chromeManager == null)
				chromeManager = new ChromeManager();
			return chromeManager;
		}
		case "firefox": {
			if (firefoxManager == null)
				firefoxManager = new FirefoxManager();
			return firefoxManager;
		}
		default:
			throw new IllegalArgumentException("Invalid browser, please check global.properties file");
		}
	}

}
