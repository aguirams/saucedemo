package stepDefinition;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import ObjectRepository.*;

public class SwagLabs {
	
	public static WebDriver driver;
	public WebDriverWait wait;
	
	@Before
	public void beforeScenario() throws InterruptedException {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\rtagu\\Desktop 2\\SELF DEVELOPMENT\\Selenium\\Webdriver\\msedgedriver.exe");
		
		// Open Edge browser
		driver = new EdgeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		// Go to Swag Labs
		driver.get("https://www.saucedemo.com");
        TimeUnit.SECONDS.sleep(5);
	}
	
	@After
	public void afterScenario() throws InterruptedException 
	{
		universalObjects uObj = new universalObjects(driver);
		
		int pageTitleCount = uObj.getPageTitleCount();
		if(pageTitleCount != 0) {
			// Log out from Swag Labs
			uObj.clickBurgerMenu();
		    TimeUnit.SECONDS.sleep(2);
		    uObj.clickLogout();
		}
	    TimeUnit.SECONDS.sleep(2);
	    
	    // Close browser
		driver.quit();
	    TimeUnit.SECONDS.sleep(5);
	}
	
	@Given("^User is on Swag Labs Home Page$")
	public void user_is_on_Swag_Labs_Home_Page() throws Throwable {
		universalObjects uObj = new universalObjects(driver);
		
		// Validate page title
		System.out.println("The page title is : " + uObj.getTitle());
		Assert.assertEquals(uObj.getTitle(), "Swag Labs");
		}

	@Given("^User is logged in to Swag Labs$")
	public void user_is_logged_in_to_Swag_Labs() throws Throwable {
		universalObjects uObj = new universalObjects(driver);
		loginObjects logObj = new loginObjects(driver);
		
		// Login to Swag Labs
		System.out.println("The page title is : " + uObj.getTitle() );
		logObj.enterUsername("standard_user");
		logObj.enterPassword("secret_sauce");
		logObj.clickLogin();
        TimeUnit.SECONDS.sleep(5);
		}
	
	@And("^Swag Labs Inventory Page is displayed$")
	public void swag_Labs_Inventory_Page_is_displayed() throws Throwable {
		universalObjects uObj = new universalObjects(driver);
		inventoryObjects invObj = new inventoryObjects(driver);
		
		System.out.println("The page title is : " + uObj.getPageTitle() );
		
		// Check inventory item count = 6
		Assert.assertEquals(6, invObj.getInventoryItemCount());
		
		// Check sort options = 4
		Assert.assertEquals(4, invObj.getSortOptionsCount());
	}
	
	@When("^User logs in with UserName as \"(.*)\" and Password as \"(.*)\"$")
	public void user_logs_in_with_UserName_as_and_Password_as(String username, String password) throws Throwable {
		loginObjects logObj = new loginObjects(driver);
		
		// Log in to Swag Labs
        logObj.enterUsername(username);
		logObj.enterPassword(password);
		logObj.clickLogin();
        TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User redirects to inventory page without logging in$")
	public void user_redirects_to_inventory_page_without_logging_in() throws Throwable {
		// Go straight to Inventory Page
		driver.get("https://www.saucedemo.com/inventory.html");
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Then("^User is redirected to Inventory Page$")
	public void user_is_redirected_to_Inventory_Page() throws Throwable {
		universalObjects uObj = new universalObjects(driver);
		
		// Verify page title
		Assert.assertEquals(uObj.getPageTitle(), "Products");
	}
	
	@Then("^Error message \"(.*)\" is displayed$")
	public void error_message_is_displayed (String errorMessage) throws Throwable {
		loginObjects logObj = new loginObjects(driver);
		
		// Verify error message
		if(errorMessage.equals("Special")) {
			assert logObj.getErrorMessage().matches("Epic sadface: You can only access '.+' when you are logged in\\.");
		} else {
			Assert.assertEquals(errorMessage, logObj.getErrorMessage());
		}
	}
	
	@When("^User sorts the items from A to Z$")
	public void user_sorts_the_items_from_A_to_Z() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Sort items from A to Z
		String sortOption = invObj.getSortOption(0);
		Assert.assertEquals("Name (A to Z)", sortOption);
		invObj.selectSortOption(sortOption);
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals("Name (A to Z)", invObj.getActiveSort());
	}
	
	@Then("^Inventory items are sorted from A to Z$")
	public void inventory_items_are_sorted_from_A_to_Z() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Check if sorting is correct
		String[] inventoryItem = new String[6];
		
		for (int i = 0; i < 6; i++) {
			inventoryItem[i] = invObj.getInventoryNames(i);
		}
		int j = 1;
		String status = "True";
		for (int i = 0; i < 5; i++) {
			int compare = inventoryItem[i].compareToIgnoreCase(inventoryItem[j]);
			if(compare >= 0) {
				System.out.println("Inventory Item " + inventoryItem[i] + " is sorted higher than Inventory Item " + inventoryItem[j] + ".");
				status = "False";
			}
			Assert.assertNotEquals("False", status);
			j++;
		}
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User sorts the items from Z to A$")
	public void user_sorts_the_items_from_Z_to_A() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Sort items by name from Z to A
		String sortOption = invObj.getSortOption(1);
		Assert.assertEquals("Name (Z to A)", sortOption);
		invObj.selectSortOption(sortOption);
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals("Name (Z to A)", invObj.getActiveSort());
	}
	
	@Then("^Inventory items are sorted from Z to A$")
	public void inventory_items_are_sorted_from_Z_to_A() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Check if sorting is correct
		String[] inventoryItem = new String[6];
		
		for (int i = 0; i < 6; i++) {
			inventoryItem[i] = invObj.getInventoryNames(i);
		}
		int j = 1;
		String status = "True";
		for (int i = 0; i < 5; i++) {
			int compare = inventoryItem[i].compareToIgnoreCase(inventoryItem[j]);
			if(compare <= 0) {
				System.out.println("Inventory Item " + inventoryItem[i] + " is sorted higher than Inventory Item " + inventoryItem[j] + ".");
				status = "False";
			}
			Assert.assertNotEquals("False", status);
			j++;
		}
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User sorts the items from low to high price$")
	public void user_sorts_the_items_from_low_to_high_price() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Sort items by price from low to high
		String sortOption = invObj.getSortOption(2);
		Assert.assertEquals("Price (low to high)", sortOption);
		invObj.selectSortOption(sortOption);
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals("Price (low to high)", invObj.getActiveSort());
	}
	
	@Then("^Inventory items are sorted from low to high price$")
	public void inventory_items_are_sorted_from_low_to_high_price() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Check if sorting is correct
		double[] inventoryPrice = new double[6];
		for (int i = 0; i < 6; i++) {
			String inventoryPriceWithCurrency = invObj.getInventoryPrices(i);
			inventoryPrice[i] = Double.parseDouble(inventoryPriceWithCurrency.replace("$", ""));
		}
		int j = 1;
		String status = "True";
		for (int i = 0; i < 5; i++) {
			if(inventoryPrice[i] > inventoryPrice[j]) {
				System.out.println("Inventory Item with Price " + inventoryPrice[i] + " is sorted higher than Inventory Item with Price " + inventoryPrice[j] + ".");
				status = "False";
			}
			Assert.assertNotEquals("False", status);
			j++;
		}
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User sorts the items from high to low price$")
	public void user_sorts_the_items_from_high_to_low_price() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Sort items by price from high to low
		String sortOption = invObj.getSortOption(3);
		Assert.assertEquals("Price (high to low)", sortOption);
		invObj.selectSortOption(sortOption);
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals("Price (high to low)", invObj.getActiveSort());
	}
	
	@Then("^Inventory items are sorted from high to low price$")
	public void inventory_items_are_sorted_from_high_to_low_price() throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Check if sorting is correct
		double[] inventoryPrice = new double[6];
		for (int i = 0; i < 6; i++) {
			String inventoryPriceWithCurrency = invObj.getInventoryPrices(i);
			inventoryPrice[i] = Double.parseDouble(inventoryPriceWithCurrency.replace("$", ""));
		}
		int j = 1;
		String status = "True";
		for (int i = 0; i < 5; i++) {
			if(inventoryPrice[i] < inventoryPrice[j]) {
				System.out.println("Inventory Item with Price " + inventoryPrice[i] + " is sorted higher than Inventory Item with Price " + inventoryPrice[j] + ".");
				status = "False";
			}
			Assert.assertNotEquals("False", status);
			j++;
		}
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User clicks on the inventory item \"(.*)\" name$")
	public void user_clicks_on_the_inventory_item_name(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Click inventory name
		invObj.clickInventoryItem(itemName);
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User clicks on the inventory item \"(.*)\" image$")
	public void user_clicks_on_the_inventory_item_image(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Click inventory image
		invObj.clickInventoryImage(itemName);
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Then("^User is redirected to details page for item \"(.*)\" with price \"(.*)\"$")
	public void user_is_redirected_to_item_details_page_for_item_with_price(String itemName, String itemPrice) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Verify item details
		Assert.assertEquals(itemName, invObj.getItemLabel());
		Assert.assertEquals(itemPrice, invObj.getItemPriceLabel());
		Assert.assertEquals(itemName, invObj.getItemImageLabel());
		Assert.assertEquals("Add to cart", invObj.getAddToCartLabel());
		invObj.clickBackToProducts();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User clicks the Add to Cart button for item \"(.*)\"$")
	public void user_clicks_the_Add_to_Cart_button_for_item(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Click add to cart button
		invObj.clickAddToCartInvPage(itemName);
		TimeUnit.SECONDS.sleep(2);
	}
	
	@When("^User clicks the Add to Cart button for item \"(.*)\" in details page$")
	public void user_clicks_the_Add_to_Cart_button_for_item_in_details_page(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		
		// Click add to cart button
		invObj.clickAddToCartDetPage(itemName);
		TimeUnit.SECONDS.sleep(2);
	}
	
	@When("^User adds item \"(.*)\" and item \"(.*)\" to cart$")
	public void user_adds_item_and_item_to_cart(String itemName1, String itemName2) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Add first item to cart
		invObj.clickAddToCartInvPage(itemName1);
		TimeUnit.SECONDS.sleep(2);
		Assert.assertEquals("Remove", invObj.getAddOrRemoveButtonTextInvPage(itemName1));
		Assert.assertEquals("1", uObj.getCartBadgeNumber());

		// Add second item to cart
		invObj.clickInventoryItem(itemName2);
		TimeUnit.SECONDS.sleep(5);
		invObj.clickAddToCartDetPage(itemName2);
		TimeUnit.SECONDS.sleep(2);
		Assert.assertEquals("Remove", invObj.getAddOrRemoveButtonTextDetPage(itemName2));
		Assert.assertEquals("2", uObj.getCartBadgeNumber());
		
		// Go to shopping cart
		uObj.clickShoppingCart();
		TimeUnit.SECONDS.sleep(2);
	}
	
	@Then("^Inventory item \"(.*)\" is added to cart$")
	public void inventory_item_is_added_to_cart(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify add to cart button is clicked
		Assert.assertEquals("Remove", invObj.getAddOrRemoveButtonTextInvPage(itemName));
		Assert.assertEquals("1", uObj.getCartBadgeNumber());
	}
	
	@Then("^Inventory item \"(.*)\" is added to cart from details page$")
	public void inventory_item_is_added_to_cart_from_details_page(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify add to cart button is clicked
		Assert.assertEquals("Remove", invObj.getAddOrRemoveButtonTextDetPage(itemName));
		Assert.assertEquals("1", uObj.getCartBadgeNumber());
	}
	
	@When("^User goes to cart page$")
	public void user_goes_to_cart_page_with_item() throws Throwable {
		universalObjects uObj = new universalObjects(driver);
		
		// Go to shopping cart
		uObj.clickShoppingCart();
		TimeUnit.SECONDS.sleep(2);
	}
	
	@Then("^Added item \"(.*)\" is in the cart$")
	public void added_item_is_in_the_cart(String itemName) throws Throwable {
		universalObjects uObj = new universalObjects(driver);
		paymentObjects payObj = new paymentObjects(driver);
		
		// Verify item is in cart
		Assert.assertEquals("Your Cart", uObj.getPageTitle());
		Assert.assertEquals(itemName, payObj.getCartItemName());
        Assert.assertEquals(1, payObj.countItemsInCart());
	}
	
	@Then("^Added item \"(.*)\" and item \"(.*)\" are in the cart$")
	public void added_item_and_item_are_in_the_cart(String itemName1, String itemName2) throws Throwable {
		universalObjects uObj = new universalObjects(driver);
		paymentObjects payObj = new paymentObjects(driver);
		
		// Verify items are in cart
		Assert.assertEquals("Your Cart", uObj.getPageTitle());
		Assert.assertEquals(2, payObj.countItemsInCart());
		Assert.assertEquals(1, payObj.countItemInCartWithRef(itemName1));
		Assert.assertEquals(1, payObj.countItemInCartWithRef(itemName2));
	}
	
	@When("^User removes item \"(.*)\" from cart$")
	public void user_removes_item_from_cart(String itemName) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Remove item from cart
		payObj.clickRemoveItem(itemName);
		Assert.assertEquals(0, uObj.getCartBadgeCount());
		TimeUnit.SECONDS.sleep(2);
	}
	
	@When("^User removes items \"(.*)\" and \"(.*)\" from cart$")
	public void user_removes_items_and_from_cart(String itemName1, String itemName2) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Remove items from cart
		payObj.clickRemoveItem(itemName1);
		TimeUnit.SECONDS.sleep(2);
		Assert.assertEquals("1", uObj.getCartBadgeNumber());
		payObj.clickRemoveItem(itemName2);
		TimeUnit.SECONDS.sleep(2);
		Assert.assertEquals(0, uObj.getCartBadgeCount());
	}
	
	@Then("^Item \"(.*)\" previously added to cart is removed$")
	public void item_previously_added_to_cart_is_removed(String itemName) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Item is no longer in cart
        Assert.assertEquals(0, payObj.countItemInCartWithRef(itemName));
        Assert.assertEquals(0, uObj.getCartBadgeCount());
        payObj.clickContinueShopping();
        TimeUnit.SECONDS.sleep(2);
	}
	
	@Then("^Item cart is empty$")
	public void item_cart_is_empty() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify cart is empty
		Assert.assertEquals(0, payObj.countItemsInCart());
		Assert.assertEquals(0, uObj.getCartBadgeCount());
		payObj.clickContinueShopping();
        TimeUnit.SECONDS.sleep(2);
	}
	
	@When("^Inventory item \"(.*)\" is removed from cart in inventory page$")
	public void inventory_item_is_removed_from_cart_in_inventory_page(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify item is removed
		invObj.clickRemoveInvPage(itemName);
		TimeUnit.SECONDS.sleep(2);
		Assert.assertEquals(1, invObj.countAddToCartInvPage(itemName));
		Assert.assertEquals(0, uObj.getCartBadgeCount());
	}
	
	@When("^Inventory item \"(.*)\" is removed from cart in details page$")
	public void inventory_item_is_removed_from_cart_in_details_page(String itemName) throws Throwable {
		inventoryObjects invObj = new inventoryObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify item is removed
		invObj.clickRemoveDetPage(itemName);
		TimeUnit.SECONDS.sleep(2);
		Assert.assertEquals(1, invObj.countAddToCartDetPage(itemName));
		Assert.assertEquals(0, uObj.getCartBadgeCount());
	}
	
	@Then("^Item is not in the cart$")
	public void item_is_removed_from_cart() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify cart is empty
		Assert.assertEquals(0, uObj.getCartBadgeCount());
        uObj.clickShoppingCart();
		TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(0, payObj.countItemsInCart());
        payObj.clickContinueShopping();
        TimeUnit.SECONDS.sleep(2);
	}
	
	@When("^User clicks the Checkout button$")
	public void user_clicks_the_Checkout_button() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Click checkout button
		payObj.clickCheckoutButton();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Then("^Checkout page is displayed$")
	public void checkout_page_is_displayed() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify checkout page is displayed
		Assert.assertEquals("Checkout: Your Information", uObj.getPageTitle());
		Assert.assertEquals(3, payObj.countCheckoutFormFields());
		Assert.assertEquals(1, payObj.countFirstNameField());
		Assert.assertEquals(1, payObj.countLastNameField());
		Assert.assertEquals(1, payObj.countPostalCodeField());
	}
	
	@When("^User inputs First Name \"(.*)\" Last Name \"(.*)\" Postal \"(.*)\"$")
	public void user_inputs_First_Name_Last_Name_Postal(String firstName, String lastName, String postCode) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Input customer details
		payObj.setFirstName(firstName);
		payObj.setLastName(lastName);
		payObj.setPostalCode(postCode);
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User inputs only Last Name \"(.*)\" Postal \"(.*)\"$")
	public void user_inputs_only_Last_Name_Postal(String lastName, String postCode) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Input incomplete customer details
		payObj.setLastName(lastName);
		payObj.setPostalCode(postCode);
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User inputs only First Name \"(.*)\" Postal \"(.*)\"$")
	public void user_inputs_only_First_Name_Postal(String firstName, String postCode) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Input incomplete customer details
		payObj.setFirstName(firstName);
		payObj.setPostalCode(postCode);
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User inputs only First Name \"(.*)\" Last Name \"(.*)\"$")
	public void user_inputs_only_First_Name_Last_Name(String firstName, String lastName) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Input incomplete customer details
		payObj.setFirstName(firstName);
		payObj.setLastName(lastName);
		TimeUnit.SECONDS.sleep(5);
	}
	
	@And("^User clicks the Continue button$")
	public void user_clicks_the_Continue_button() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Click continue
		payObj.clickContinue();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@When("^User clicks the Cancel button$")
	public void user_clicks_the_Cancel_button() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Click cancel
		payObj.clickCancel();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Then("^User is redirected to cart page with item \"(.*)\" still in cart")
	public void user_is_redirected_to_cart_page_with_item_still_in_cart(String itemName) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Verify user is back in cart
		Assert.assertEquals("Your Cart", uObj.getPageTitle());
		Assert.assertEquals(itemName, payObj.getCartItemName());
        payObj.clickContinueShopping();
        TimeUnit.SECONDS.sleep(2);
	}
	
	@Then("^Checkout Overview page is diplayed with item \"(.*)\" details$")
	public void checkout_Overview_page_is_diplayed_with_item_details(String itemName) throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Checkout overview is displayed
		Assert.assertEquals("Checkout: Overview", uObj.getPageTitle());
		Assert.assertEquals(itemName, payObj.getItemInCheckoutName());
		Assert.assertEquals(1, payObj.countPaymentInformation());
		Assert.assertEquals(1, payObj.countShippingInformation());
		Assert.assertEquals(1, payObj.countPriceTotal());
		Assert.assertEquals(1, payObj.countTotalPrice());
	}
	
	@When("^User clicks the Finish button$")
	public void user_clicks_the_Finish_button() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Click finish button
		payObj.clickFinish();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Then("^Order is completed$")
	public void order_is_completed() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		universalObjects uObj = new universalObjects(driver);
		
		// Order is completed
		Assert.assertEquals("Checkout: Complete!", uObj.getPageTitle());
		Assert.assertEquals("Thank you for your order!", payObj.getThankYouMessage());
		payObj.clickBackToHome();
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals("Products", uObj.getPageTitle());
	}
	
	@Then("^error message for first name is displayed$")
	public void error_message_for_first_name_is_displayed() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Error message is displayed
		Assert.assertEquals("Error: First Name is required", payObj.getErrorMessage());
		payObj.clearError();
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals(0, payObj.countErrors());
		
		// Refresh page
		driver.navigate().refresh();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Then("^error message for last name is displayed$")
	public void error_message_for_last_name_is_displayed() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Error message is displayed
		Assert.assertEquals("Error: Last Name is required", payObj.getErrorMessage());
		payObj.clearError();
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals(0, payObj.countErrors());
		
		// Refresh page
		driver.navigate().refresh();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Then("^error message for postal code is displayed$")
	public void error_message_for_postal_code_is_displayed() throws Throwable {
		paymentObjects payObj = new paymentObjects(driver);
		
		// Error message is displayed
		Assert.assertEquals("Error: Postal Code is required", payObj.getErrorMessage());
		payObj.clearError();
		TimeUnit.SECONDS.sleep(5);
		Assert.assertEquals(0, payObj.countErrors());
		
		// Refresh page
		driver.navigate().refresh();
		TimeUnit.SECONDS.sleep(5);
	}

}