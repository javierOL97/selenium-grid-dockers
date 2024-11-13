package stepDefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import browserManager.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pageObjects.PageObjectsFactory;
import singletonManager.SingletonFactory;
import utils.GlobalPropertiesLoader;
import utils.Keywords;

public class Hooks {

	private WebDriver driver;

	@Before
	public void driverSetUp() {
		//Initializing DriverManager singleton to get our WebDriver.
		driver = SingletonFactory.getSingletonInstance(DriverFactory.class).getBrowserManager().getDriver();
		// Setting webdriver to PageObjectMapper
		SingletonFactory.getSingletonInstance(PageObjectsFactory.class).setDriver(driver);
		
		// Navigating to HomePage.
		driver.manage().window().maximize();
		driver.get(SingletonFactory.getSingletonInstance(GlobalPropertiesLoader.class)
				.getProperty(Keywords.URL.toString()));
	}

	@After
	public void afterScenario() throws IOException {
		driver.quit();
		SingletonFactory.cleanObjectFactory();
	}

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
			scenario.attach(fileContent, "image/png", "image");
			SingletonFactory.cleanObjectFactory();
			//Suggest running the Garbage Collector.
			System.gc();
		}

	}
}
