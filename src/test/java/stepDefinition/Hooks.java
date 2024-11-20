package stepDefinition;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

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

	private volatile WebDriver driver;

	@Before
	public void setUp() {
		//Initializing DriverManager singleton to get our WebDriver.
		SingletonFactory.getSingletonInstance(GlobalPropertiesLoader.class);
		driver = SingletonFactory.getSingletonInstance(DriverFactory.class).getBrowserManager().getDriver();
		// Setting webdriver to PageObjectMapper
		SingletonFactory.getSingletonInstance(PageObjectsFactory.class).setDriver(driver);
		
		// Navigating to HomePage.
		driver.manage().window().maximize();
		driver.navigate().to(SingletonFactory.getSingletonInstance(GlobalPropertiesLoader.class)
				.getProperty(Keywords.URL.toString()));
	}

	@After
	public void afterScenario() throws IOException {
		driver.quit();
		SingletonFactory.cleanObjectFactory();
		//Suggest running the Garbage Collector.
		System.gc();
	}

	@AfterStep
	public void AddScreenshot(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			String sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
			String htmlImage = String.format("<img width=700px src='data:image/png;base64,%s' />", sourcePath);
			Reporter.log(htmlImage);
			
			SingletonFactory.cleanObjectFactory();
			//Suggest running the Garbage Collector.
			System.gc();
		}
	}
}
