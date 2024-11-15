package browserManager;

import utils.Keywords;

public class DriverFactory {

	private IBrowserManager chromeManager;
	private IBrowserManager firefoxManager;

	private DriverFactory() {

	}

	public IBrowserManager getBrowserManager() {

		switch (System.getProperty(Keywords.BROWSER.toString())) {
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
