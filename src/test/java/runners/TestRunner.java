package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/java/features" }, 
		glue = {"stepDefinition" },
		monochrome = true,
		tags = "@Regression",
		// plugin =// {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		
		dryRun = false)
public class TestRunner extends AbstractTestNGCucumberTests {

}
