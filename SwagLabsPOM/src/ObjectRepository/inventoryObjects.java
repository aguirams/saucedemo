package ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class inventoryObjects {
	
	WebDriver driver;
	
	public inventoryObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@class='inventory_item']")
	List<WebElement> inventoryItems;
	
	@FindBy(xpath = "//select[@class='product_sort_container']")
	WebElement sortField;
	
	@FindBy(xpath = "//select[@class='product_sort_container']/option")
	List<WebElement> sortOptions;
	
	@FindBy(xpath = "//span[@class='active_option']")
	WebElement activeSort;
	
	@FindBy(xpath = "//div[@class='inventory_item_name']")
	List<WebElement> inventoryItemNames;
	
	@FindBy(xpath = "//div[@class='inventory_item_price']")
	List<WebElement> inventoryPrices;
	
	@FindBy(xpath = "//div[contains(@class, 'inventory_details_name large_size')]")
	WebElement itemLabel;
	
	@FindBy(xpath = "//div[contains(@class, 'inventory_details_price')]")
	WebElement itemPriceLabel;
	
	@FindBy(xpath = "//img[@class='inventory_details_img']")
	WebElement itemImage;
	
	@FindBy(xpath = "//button[contains(@id, 'add-to-cart')]")
	WebElement addToCart;
	
	@FindBy(id = "back-to-products")
	WebElement backToProducts;
	
	// Page Actions
	
	public int getInventoryItemCount() {
		int inventoryItemCount = inventoryItems.size();
		return inventoryItemCount;
	}
	
	public int getSortOptionsCount() {
		int sortOptionsCount = sortOptions.size();
		return sortOptionsCount;
	}
	
	public String getSortOption(int sortIndex) {
		Select sort = new Select(sortField);
		List<WebElement> sortList = sort.getOptions();
		String optionsList = sortList.get(sortIndex).getAttribute("text");
		return optionsList;
	}
	
	public void selectSortOption(String sortOption) {
		Select sort = new Select(sortField);
		sort.selectByVisibleText(sortOption);
	}
	
	public String getActiveSort() {
		String activeSortOption = activeSort.getText();
		return activeSortOption;
	}
	
	public String getInventoryNames (int priceIndex) {
		String inventoryName = inventoryItemNames.get(priceIndex).getText();
		return inventoryName;
	}
	
	public String getInventoryPrices (int priceIndex) {
		String inventoryPrice = inventoryPrices.get(priceIndex).getText();
		return inventoryPrice;
	}
	
	public void clickInventoryItem (String itemName) {
		WebElement inventoryItem = driver.findElement(By.xpath("//div[contains(@class, 'inventory_item_name') and text() = '" + itemName +"']"));
		inventoryItem.click();
	}
	
	public void clickInventoryImage (String itemName) {
		WebElement inventoryImage = driver.findElement(By.xpath("//img[@alt='" + itemName +"']"));
		inventoryImage.click();
	}
	
	public String getItemLabel () {
		String itemLabelName = itemLabel.getText();
		return itemLabelName;
	}
	
	public String getItemPriceLabel () {
		String itemPriceLabelText = itemPriceLabel.getText();
		return itemPriceLabelText;
	}
	
	public String getItemImageLabel () {
		String itemImageText = itemImage.getAttribute("alt");
		return itemImageText;
	}
	
	public String getAddToCartLabel () {
		String addToCartText = addToCart.getText();
		return addToCartText;
	}
	
	public void clickBackToProducts () {
		backToProducts.click();
	}
	
	public void clickAddToCartInvPage(String itemName) {
		WebElement addToCartButton = driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[contains(@id,'add-to-cart')]"));
		addToCartButton.click();
	}
	
	public void clickAddToCartDetPage(String itemName) {
		WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='inventory_details_desc_container' and descendant::div[text()='" + itemName + "']]//button[contains(@id,'add-to-cart')]"));
		addToCartButton.click();
	}
	
	public String getAddOrRemoveButtonTextInvPage(String itemName) {
		WebElement addOrRemoveButton = driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button"));
		String addOrRemoveButtonText = addOrRemoveButton.getText();
		return addOrRemoveButtonText;
	}
	
	public String getAddOrRemoveButtonTextDetPage(String itemName) {
		WebElement addOrRemoveButton = driver.findElement(By.xpath("//div[@class='inventory_details_desc_container' and descendant::div[text()='" + itemName + "']]//button"));
		String addOrRemoveButtonText = addOrRemoveButton.getText();
		return addOrRemoveButtonText;
	}
	
	public void clickRemoveInvPage(String itemName) {
		WebElement removeButton = driver.findElement(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[contains(@id,'remove')]"));
		removeButton.click();
	}
	
	public void clickRemoveDetPage(String itemName) {
		WebElement removeButton = driver.findElement(By.xpath("//div[@class='inventory_details_desc_container' and descendant::div[text()='" + itemName + "']]//button[contains(@id,'remove')]"));
		removeButton.click();
	}
	
	public int countAddToCartInvPage(String itemName) {
		List<WebElement> addToCartButton = driver.findElements(By.xpath("//div[text()='" + itemName + "']/ancestor::div[@class='inventory_item']//button[contains(@id,'add-to-cart')]"));
		int countAddToCart = addToCartButton.size();
		return countAddToCart;
	}
	
	public int countAddToCartDetPage(String itemName) {
		List<WebElement> addToCartButton = driver.findElements(By.xpath("//div[@class='inventory_details_desc_container' and descendant::div[text()='" + itemName + "']]//button[contains(@id,'add-to-cart')]"));
		int countAddToCart = addToCartButton.size();
		return countAddToCart;
	}


}
