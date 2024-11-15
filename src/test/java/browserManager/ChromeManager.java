package browserManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.FWLogger;
import utils.Keywords;

public class ChromeManager implements IBrowserManager {
	private WebDriver driver;

	@Override
	public WebDriver getDriver() {
		//If our selenium.grid.enabled System Property is true, test will be 
		//executed using RemoteDriver.
		if (driver == null && Boolean.getBoolean("selenium.grid.enabled")) {
			Capabilities capabilities = new ChromeOptions();
			try {
				driver = new RemoteWebDriver(new URL(Keywords.REMOTEURL.toString()), capabilities);
			} catch (MalformedURLException e) {
				FWLogger.error("There's an error while creating RemoteDriver: " +e);
			}
		}
		//If our selenium.grid.enabled System Property is true, test will be 
		//executed using ChromeDriver.
		else if(driver == null && !Boolean.getBoolean("selenium.grid.enabled")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			}
		return driver;
	}

}
