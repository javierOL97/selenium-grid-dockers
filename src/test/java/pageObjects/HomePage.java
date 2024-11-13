package pageObjects;

import org.openqa.selenium.*;

import utils.GenericActions;

public class HomePage {

	private final WebDriver driver;
	private final GenericActions actions;

	public HomePage(WebDriver driver, GenericActions actions) {
		this.driver = driver;
		this.actions = actions;
	}

	// --------------------- Locators ---------------------
	// Navbar locators
	public final By homeButton = By.cssSelector(".navbar-nav > li:nth-child(1) > a");
	public final By productButton = By.cssSelector(".navbar-nav > li:nth-child(2) > a");
	public final By cartButton = By.cssSelector(".navbar-nav > li:nth-child(3) > a");
	public final By signLoginButton = By.cssSelector(".navbar-nav > li:nth-child(4) > a");
	public final By testCasesButton = By.cssSelector(".navbar-nav > li:nth-child(5) > a");
	public final By apiTestingButton = By.cssSelector(".navbar-nav > li:nth-child(6) > a");
	public final By tutorialsButton = By.cssSelector(".navbar-nav > li:nth-child(7) > a");
	public final By contactUsButton = By.cssSelector(".navbar-nav > li:nth-child(8) > a");
	public final By deleteAccountButton = By.cssSelector("a[href='/delete_account']");

	// Slider-Carousel banner locators
	public final By prevCarouselButton = By.cssSelector("#slider-carousel > a[data-slide='prev']");
	public final By nextCarouselButton = By.cssSelector("#slider-carousel > a[data-slide='next']");
	public final By carouselIndicators = By.cssSelector("ol.carousel-indicators > li");

	// Category Section
	public final By womenCategoryButton = By.cssSelector("a[href='#Women']");
	public final By menCategoryButton = By.cssSelector("a[href='#Men']");
	public final By kidsCategoryButton = By.cssSelector("a[href='#Kids']");
	public final By womenProductsOptions = By.cssSelector("#Women > div.panel-body > ul > li");
	public final By menProductsOptions = By.cssSelector("#Men > div.panel-body > ul > li");
	public final By kidsProductsOptions = By.cssSelector("#Kids > div.panel-body > ul > li");

	// Features Items
	public final By itemCards = By.cssSelector("div.features_items > div.col-sm-4");
	// We need to hover our mouse to an item card first if we want to make Add to
	// cart button clickable.
	public final By addToCartButtons = By.cssSelector("div.overlay-content > a.add-to-cart");
	public final By viewProductButtons = By.linkText("View Product");
	public final By productPrice = By.cssSelector("div.productinfo > h2");
	public final By productName = By.cssSelector("div.productinfo > p");

	// Recommended Items
	public final By recommendedAddToCartButton = By.cssSelector("");
	public final By nextCorouselButton = By.cssSelector("#recommended-item-carousel > a[data-slide='next']");
	public final By prevCorouselButton = By.cssSelector("#recommended-item-carousel > a[data-slide='prev']");
	public final By carouselItems = By.cssSelector("#recommended-item-carousel > div.carousel-inner > div");

	// Added banner after adding some product to cart
	public final By continueShoppingButton = By.linkText("Continue Shopping");
	public final By viewCartButton = By.linkText("View Cart");
	public final By productAddedMessage = By.linkText("Your product has been added to cart.");

	// ------------------------------ HomePage Methods ------------------------------

	public void moveToLoginPage() {
		actions.clickElement(signLoginButton);
	}

	public void deleteAccount() {
		actions.clickElement(deleteAccountButton);
	}

}