package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "classpath:features" }, 
		glue = {"stepDefinition" },
		monochrome = true,
		tags = "@Smoke and @SmokeFail",
		// plugin =// {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
		
		dryRun = false)
public class SmokeRunner extends AbstractTestNGCucumberTests {
	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
 