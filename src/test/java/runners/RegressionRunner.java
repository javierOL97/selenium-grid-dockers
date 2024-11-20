package runners;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/java/features" }, 
		glue = {"stepDefinition" },
		monochrome = true,
		tags = "@Regression",
		// plugin =// {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		
		dryRun = false)
@Test
public class RegressionRunner extends AbstractTestNGCucumberTests {
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
 