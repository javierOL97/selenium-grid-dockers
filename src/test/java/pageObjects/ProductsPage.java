package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.GenericActions;

public class ProductsPage {

	private final WebDriver driver;
	private final GenericActions actions;

	public ProductsPage(WebDriver driver, GenericActions actions) {
		this.driver = driver;
		this.actions = actions;
	}

	// --------------------- Locators ---------------------
	// Detailed product page
	public By quantityInput = By.id("quantity");
	public By detailAddToCart = By.partialLinkText("Add to cart");
	public By detailProductTitle = By.cssSelector(".product-information > h2");
	public By detailedPrice = By.partialLinkText("Rs. ");

	// Review
	public By nameTxtField = By.id("name");
	public By emailTxtField = By.id("email");
	public By reviewTxtField = By.id("review");
	public By submitButton = By.id("button-review");

	// Brands
	public By brandsLinks = By.cssSelector(".brands-name li");

	// All Products
	public By searchProductTxtField = By.id("search_product");
	public By searchProductButton = By.id("submit_search");

	public By itemCards = By.cssSelector("div.features_items > div.col-sm-4");
	// We need to hover our mouse to an item card first if we want to make Add to cart button clickable.
	public By addToCartButtons = By.cssSelector("div.overlay-content > a.add-to-cart");
	public By viewProductButtons = By.linkText("View Product");
	public By productPrice = By.cssSelector("div.productinfo > h2");
	public By productName = By.cssSelector("div.productinfo > p");

	// Added banner after adding some product to cart
	public By continueShoppingButton = By.linkText("Continue Shopping");
	public By viewCartButton = By.linkText("View Cart");
	public By productAddedMessage = By.linkText("Your product has been added to cart.");

	// ------------------------------ ProductsPage Methods ------------------------------

}
