package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.GenericActions;

public class CartPage {

	private final WebDriver driver;
	private final GenericActions actions;

	public CartPage(WebDriver driver, GenericActions actions) {
		this.driver = driver;
		this.actions = actions;
	}

	// --------------------- Locators ---------------------
	// When cart is empty
	public By emptyCartMessage = By.partialLinkText("is empty!");

	// When cart isn't empty
	public By multipleItemsList = By.cssSelector("#cart_info_table > tbody > tr");

	public By proceedToCheckoutButton = By.linkText("Proceed To Checkout");
	public By productDescription = By.cssSelector(".cart_description a");
	public By productPrice = By.cssSelector(".cart_price p");
	public By productQuantity = By.cssSelector(".cart_quantity button");
	public By productTotal = By.cssSelector("p.cart_total_price");
	public By productDelete = By.cssSelector("a.cart_quantity_delete");

	// ------------------------------ CartPage Methods ------------------------------

}
