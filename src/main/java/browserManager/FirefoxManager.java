package browserManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import singletonManager.SingletonFactory;
import utils.FWLogger;
import utils.GlobalPropertiesLoader;
import utils.Keywords;

public class FirefoxManager implements IBrowserManager {

	private WebDriver driver;

	@Override
	public WebDriver getDriver() {
		final GlobalPropertiesLoader prop = SingletonFactory.getSingletonInstance(GlobalPropertiesLoader.class);

		// If our selenium.grid.enabled System Property is true, test will be
		// executed using RemoteDriver.
		if (driver == null && Boolean.valueOf(prop.getProperty(Keywords.SELENIUM_GRID_ENABLED.toString()))) {
			FirefoxOptions options = new FirefoxOptions();
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			options.setPageLoadTimeout(Duration.of(15, ChronoUnit.SECONDS));
			try {
				// Getting selenium grid URL from maven system properties.
				String remoteURL = String.format(prop.getProperty(Keywords.SELENIUM_GRID_URL_FORMAT.toString()),
						System.getProperty(Keywords.SELENIUM_GRID_HUB_HOST.toString()));

				driver = new RemoteWebDriver(new URL(remoteURL), options);
			} catch (MalformedURLException e) {
				FWLogger.error("There's an error while creating RemoteDriver: " + e);
			}
		}
		// If our selenium.grid.enabled System Property is true, test will be
		// executed using FirefoxDriver.
		else if (driver == null && !Boolean.valueOf(prop.getProperty(Keywords.SELENIUM_GRID_ENABLED.toString()))) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		return driver;
	}

}
