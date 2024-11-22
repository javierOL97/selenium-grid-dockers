package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.GenericActions;
import utils.Keywords;

public class LoginPage {

	private final WebDriver driver;
	private final GenericActions actions;

	public LoginPage(WebDriver driver, GenericActions actions) {
		this.driver = driver;
		this.actions = actions;
	}

	// --------------------- Locators ---------------------
	// Account Information form
	public final By accountInfoName = By.id("name");
	public final By accountInfoEmail = By.id("email");
	public final By accountInfoPwd = By.id("password");
	public final By accountInfoGenderMale = By.id("id_gender1");
	public final By accountInfoGenderFemale = By.id("id_gender2");
	public final By accountInfoDay = By.id("days");
	public final By accountInfoMonth = By.id("months");
	public final By accountInfoYear = By.id("years");

	//Address Information form
	public final By fNameTxt = By.id("first_name");
	public final By lNameTxt = By.id("last_name");
	public final By companyTxt = By.id("company");
	public final By address1Txt = By.id("address1");
	public final By address2Txt = By.id("address2");
	public final By countryDpdwn = By.id("country");
	public final By stateTxt = By.id("state");
	public final By cityTxt = By.id("city");
	public final By zipTxt = By.id("zipcode");
	public final By mobileTxt = By.id("mobile_number");
	public final By createAccountButton = By.cssSelector("button[data-qa='create-account']");

	//Login fields and button
	public final By loginEmail = By.cssSelector("input[data-qa='login-email']");
	public final By loginPwd = By.cssSelector("input[data-qa='login-password']");
	public final By loginButton = By.cssSelector("button[data-qa='login-button']");

	// SignUp fields and button
	public final By signupName = By.cssSelector("input[data-qa='signup-name']");
	public final By signupEmail = By.cssSelector("input[data-qa='signup-email']");
	public final By signupButton = By.cssSelector("button[data-qa='signup-button']");
	
	public final By continueButton = By.cssSelector("a[data-qa='continue-button']");

	// Text messages
	public final By createdAccountMessage = By.xpath("//*[contains(text(),'Account Created!')]");
	public final By deletedAccountMessage = By.xpath("//*[contains(text(),'Account Deleted!')]");

	// ------------------------------ LoginPage Methods ------------------------------
	/**
	 * Enters name and email in signin fields.
	 * 
	 * @param name  Name to be used
	 * @param email Email to be used
	 */
	public void enterNameAndEmail(String name, String email) {
		actions.waitForElementVisibility(signupEmail);
		actions.populateTextField(signupName, name);
		actions.populateTextField(signupEmail, email);
	}

	public void selectGender(String gender) {
		actions.waitForElementVisibility(accountInfoGenderMale);
		actions.moveToWebElementJs(accountInfoGenderMale);
		if (gender.equalsIgnoreCase(Keywords.MALE.toString()))
			actions.clickElement(accountInfoGenderMale);
		else
			actions.clickElement(accountInfoGenderFemale);
	}

	public void populateAccountInformation(String password, String dOb) {
		String day, year;
		int month;
		day = dOb.substring(0, 2);
		day = day.startsWith("0") ? day.substring(1, 2) : day;
		month = Integer.parseInt(dOb.substring(3, 5));
		year = dOb.substring(6);

		actions.populateTextField(accountInfoPwd, password);
		actions.selectDropDownValueByText(accountInfoDay, day);
		actions.selectDropDownValueByValue(accountInfoMonth, month);
		actions.selectDropDownValueByText(accountInfoYear, year);
	}

	public void populateAddressInformation(String fname, String lname, String company, String address, String country,
			String state, String city, String zipcode, String mobileNumber) {
		actions.populateTextField(fNameTxt, fname);
		driver.findElement(lNameTxt).sendKeys(lname);
		driver.findElement(companyTxt).sendKeys(company);
		driver.findElement(address1Txt).sendKeys(address);

		actions.selectDropDownValueByText(countryDpdwn, country);

		driver.findElement(stateTxt).sendKeys(state);
		driver.findElement(cityTxt).sendKeys(city);
		driver.findElement(zipTxt).sendKeys(zipcode);
		driver.findElement(mobileTxt).sendKeys(mobileNumber);
	}

}
