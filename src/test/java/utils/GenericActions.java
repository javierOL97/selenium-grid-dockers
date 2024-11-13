package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericActions {

	private final WebDriver driver;
	private final WebDriverWait wait;
	private final int maxWaitTime = 20;

	public GenericActions(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
	}

	public WebElement getWebElement(By locator) {
		return driver.findElement(locator);
	}

	// WaitMethods
	public void waitForElementPresence(By locator) {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElementClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementVisibility(By locator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForTextPresentInElement(By locator, String text) {
		wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	public void waitForElementInvisibility(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxWaitTime));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForElementInvisibility(By locator, int time) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void waitForStalenessOfElement(By locator) {
		wait.until(ExpectedConditions.stalenessOf(driver.findElement(locator)));
	}

	public void waitForConfirmationMessage(By locator) {
		waitForElementVisibility(locator);
		waitForElementInvisibility(locator);
	}
	
	public void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// GetProperties
	public String getProperty(String property) {
		Properties prop = new Properties();
		
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
			prop.load(fis);
		} catch (IOException e) {
			throw new RuntimeException("Failed to load global properties file, please check global.properties.");
		}
		return prop.getProperty(property);
	}

	// PopulateField
	public void populateTextField(By locator, String text) {
		waitForElementClickable(locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}

	// ClickMethods
	public void clickElement(By locator) {
		waitForElementClickable(locator);
		driver.findElement(locator).click();
	}

	public void clickElement(WebElement element) {
		waitForElementClickable(element);
		element.click();
	}

	public void moveToWebElement(WebElement target) {
		waitForElementClickable(target);
		Actions action = new Actions(driver);
		action.moveToElement(target).perform();
		action.pause(800).perform();
	}

	public void moveToWebElement(By target) {
		waitForElementClickable(target);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(target)).perform();
		action.pause(800).perform();
	}
	
	public void moveToWebElementJs(By target) {
		waitForElementVisibility(target);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(target));
	}

	// Select dropdown value

	public void selectDropDownValueByText(By locator, String text) {
		waitForElementClickable(locator);
		WebElement element = driver.findElement(locator);

		Select dropDown = new Select(element);
		try {
			boolean isOptionSelected = dropDown.getFirstSelectedOption().getText().equalsIgnoreCase(text);

			while (!isOptionSelected) {
				dropDown.selectByVisibleText(text);
				isOptionSelected = (dropDown.getFirstSelectedOption().getText().equalsIgnoreCase(text));
			}
		} catch (Exception e) {
			throw new NoSuchElementException("Select value not found: "+ text, e);
		}
		

	}
	
	public void selectDropDownValueByValue(By locator, int index) {
		waitForElementClickable(locator);
		WebElement element = driver.findElement(locator);
		try {
			Select dropDown = new Select(element);
			dropDown.selectByIndex(index);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("Select value not found: "+ index, e);
		}
		
	}

	public boolean isOptionSelected(By locator, String text) {
		Select select = new Select(getWebElement(locator));
		WebElement option = select.getFirstSelectedOption();

		boolean response = (text.equalsIgnoreCase(option.getText())) ? true : false;
		return response;
	}
}
