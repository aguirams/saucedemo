package ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class paymentObjects {
	
	WebDriver driver;
	
	public paymentObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='cart_item']")
	List<WebElement> itemsInCart;
	
	@FindBy(xpath = "//div[@class='inventory_item_name']")
	WebElement cartItemName;
	
	@FindBy(id = "continue-shopping")
	WebElement continueShoppingButton;
	
	@FindBy(id = "checkout")
	WebElement checkOutButton;
	
	@FindBy(xpath = "//div[@class='form_group']")
	List<WebElement> checkoutFormFields;
	
	@FindBy(id = "first-name")
	List<WebElement> firstNameForm;
	
	@FindBy(id = "first-name")
	WebElement firstNameField;
	
	@FindBy(id = "last-name")
	List<WebElement> lastNameForm;
	
	@FindBy(id = "last-name")
	WebElement lastNameField;
	
	@FindBy(id = "postal-code")
	List<WebElement> postalForm;
	
	@FindBy(id = "postal-code")
	WebElement postalField;
	
	@FindBy(id = "continue")
	WebElement continueButton;
	
	@FindBy(id = "cancel")
	WebElement cancelButton;
	
	@FindBy(xpath = "//div[@class='inventory_item_name']")
	WebElement itemInCheckout;
	
	@FindBy(xpath = "//div[text()='Payment Information']")
	List<WebElement> paymentInformation;
	
	@FindBy(xpath = "//div[text()='Shipping Information']")
	List<WebElement> shippingInformation;
	
	@FindBy(xpath = "//div[text()='Price Total']")
	List<WebElement> priceTotal;
	
	@FindBy(xpath = "//div[@class='summary_info_label summary_total_label']")
	List<WebElement> totalPrice;
	
	@FindBy(id = "finish")
	WebElement finishButton;
	
	@FindBy(xpath = "//h2[@class='complete-header']")
	WebElement thankYou;
	
	@FindBy(id = "back-to-products")
	WebElement backHomeButton;
	
	@FindBy(xpath = "//div[@class='error-message-container error']")
	WebElement errorMessage;
	
	@FindBy(xpath = "//div[@class='error-message-container error']")
	List<WebElement> errorContainer;
	
	@FindBy(xpath = "//button[@class='error-button']")
	WebElement removeErrors;
	
	
	
	// Page Actions
	
	public void clickAddToCart(String itemName) {
		WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[contains(@id,'add-to-cart')]"));
		addToCartButton.click();
	}
	
	public int countItemsInCart() {
		int numOfItemsInCart = itemsInCart.size();
		return numOfItemsInCart;
	}
	
	public String getCartItemName() {
		String cartItem = cartItemName.getText();
		return cartItem;
	}
	
	public int countItemInCartWithRef(String itemName) {
		List<WebElement> itemInCart = driver.findElements(By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']"));
		int numOfItemInCart = itemInCart.size();
		return numOfItemInCart;
	}
	
	public void clickRemoveItem(String itemName) {
		WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='cart_item']//button[contains(@id,'remove')]"));
		removeButton.click();
	}
	
	public void clickContinueShopping() {
		continueShoppingButton.click();
	}
	
	public void clickCheckoutButton() {
		checkOutButton.click();
	}
	
	public int countCheckoutFormFields () {
		int checkoutFieldsCount = checkoutFormFields.size();
		return checkoutFieldsCount;
	}
	
	public int countFirstNameField () {
		int firstNameFieldCount = firstNameForm.size();
		return firstNameFieldCount;
	}
	
	public int countLastNameField () {
		int lastNameFieldCount = lastNameForm.size();
		return lastNameFieldCount;
	}
	
	public int countPostalCodeField () {
		int postalFieldCount = postalForm.size();
		return postalFieldCount;
	}
	
	public void setFirstName (String firstName) {
		firstNameField.sendKeys(firstName);
	}
	
	public void setLastName (String lastName) {
		lastNameField.sendKeys(lastName);
	}
	
	public void setPostalCode (String postalCode) {
		postalField.sendKeys(postalCode);
	}
	
	public void clickContinue() {
		continueButton.click();
	}
	
	public void clickCancel() {
		cancelButton.click();
	}
	
	public String getItemInCheckoutName() {
		String itemInCheckoutName = itemInCheckout.getText();
		return itemInCheckoutName;
	}
	
	public int countPaymentInformation () {
		int paymentInformationCount = paymentInformation.size();
		return paymentInformationCount;
	}
	
	public int countShippingInformation () {
		int shippingInformationCount = shippingInformation.size();
		return shippingInformationCount;
	}
	
	public int countPriceTotal () {
		int priceTotalCount = priceTotal.size();
		return priceTotalCount;
	}
	
	public int countTotalPrice () {
		int totalPriceCount = totalPrice.size();
		return totalPriceCount;
	}
	
	public void clickFinish () {
		finishButton.click();
	}
	
	public String getThankYouMessage () {
		String thankYouMessage = thankYou.getText();
		return thankYouMessage;
	}
	
	public void clickBackToHome () {
		backHomeButton.click();
	}
	
	public String getErrorMessage () {
		String errorMessageText = errorMessage.getText();
		return errorMessageText;
	}
	
	public void clearError () {
		removeErrors.click();
	}
	
	public int countErrors () {
		int errorCount = errorContainer.size();
		return errorCount;
	}

}
