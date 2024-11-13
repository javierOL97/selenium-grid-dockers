package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import browserManager.DriverFactory;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import singletonManager.SingletonFactory;
import utils.GenericActions;
import pageObjects.*;

public class LogInPageStepDef {

	@SuppressWarnings("unused")
	private final WebDriver driver;
	private final PageObjectsFactory pageObjectFactory;

	private final GenericActions actions;
	private final HomePage homePage;
	private final LoginPage loginPage;

	public LogInPageStepDef() {
		this.driver = SingletonFactory.getSingletonInstance(DriverFactory.class).getBrowserManager().getDriver();
		this.pageObjectFactory = SingletonFactory.getSingletonInstance(PageObjectsFactory.class);
		
		this.homePage = pageObjectFactory.getPage(HomePage.class);
		this.loginPage = pageObjectFactory.getPage(LoginPage.class);
		this.actions = pageObjectFactory.getGenericActions();
	}

	@Given("User moves to signup-login page")
	public void user_moves_to_signup_login_page() throws InterruptedException {
		homePage.moveToLoginPage();
		actions.waitForElementVisibility(loginPage.signupEmail);
	}

	@And("enters his {string} and {string} in signup fields")
	public void enters_his_and_in_signup_fields(String name, String email) {
		loginPage.enterNameAndEmail(name, email);
	}

	@When("User clicks on submit button and verify that {string} title is visible")
	public void user_clicks_on_submit_button_and_verify_that_enter_account_information_title_is_visible(
			String message) {
		actions.clickElement(loginPage.signupButton);
		actions.waitForElementVisibility(loginPage.accountInfoName);
	}

	@When("Fill details as {string}, {string}, {string}, {string} and {string}")
	public void fill_details_as_and(String gender, String name, String email, String password, String dOb) {
		loginPage.selectGender(gender);
		loginPage.populateAccountInformation(password, dOb);
	}

	@When("Also fills details as {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
	public void also_fill_details_as(String name, String lname, String company, String address, String country,
			String state, String city, String zipcode, String mobileNumber) {
		
		loginPage.populateAddressInformation(name, lname, company, address, country, 
				state, city, zipcode, mobileNumber);
	}

	@Then("User press create account button")
	public void user_press_create_account_button() {
		actions.moveToWebElementJs(loginPage.createAccountButton);
		actions.clickElement(loginPage.createAccountButton);
	}

	@And("Verifies the create account success message is visible")
	public void verifies_the_create_account_success_message_is_visible() {
		actions.waitForElementVisibility(loginPage.createdAccountMessage);
	}
	
	@And("User clicks continue button and Verifies that is logged with his {string}")
	public void user_clicks_continue_button_and_verifies_that_is_logged_with_his(String name) {
		actions.clickElement(loginPage.continueButton);
		actions.waitForElementVisibility(By.partialLinkText(name));
	}
	
	@Then("User clicks on Delete account button")
	public void user_clicks_on_delete_account_button() {
		homePage.deleteAccount();
	}
	
	@And("Verifies the Delete account message is visible")
	public void verifies_the_delete_account_message_is_visible() {
		actions.waitForElementVisibility(loginPage.deletedAccountMessage);
		actions.clickElement(loginPage.continueButton);
	}

}
