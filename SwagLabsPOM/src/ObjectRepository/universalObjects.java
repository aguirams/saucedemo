package ObjectRepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class universalObjects {
	
	WebDriver driver;
	
	public universalObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//title")
	public WebElement title;
	
	@FindBy(xpath = "//span[@class='title']")
	public WebElement pageTitle;
	
	@FindBy(xpath = "//span[@class='title']")
	public List<WebElement> pageTitles;
	
	@FindBy(xpath = "//div[@class='bm-burger-button']")
	public WebElement burgerMenu;
	
	@FindBy(id = "logout_sidebar_link")
	public WebElement logoutLink;
	
	@FindBy(xpath = "//span[@class='shopping_cart_badge']")
	public WebElement cartBadge;
	
	@FindBy(xpath = "//a[@class='shopping_cart_link']")
	public WebElement shoppingCart;
	
	// Page Actions
	
	public String getTitle() {
		//String mainTitle = title.getText();
		String mainTitle = driver.getTitle();
		return mainTitle;
	}
	
	public String getPageTitle() {
		String pageLabel = pageTitle.getText();
		return pageLabel;
	}
	
	public int getPageTitleCount() {
		int pageTitleCount = pageTitles.size();
		return pageTitleCount;
	}
	
	public void clickBurgerMenu() {
		burgerMenu.click();
	}
	
	public void clickLogout() {
		logoutLink.click();
	}
	
	public String getCartBadgeNumber () {
		String cartBadgeNumber = cartBadge.getText();
		return cartBadgeNumber;
	}
	
	public int getCartBadgeCount () {
		List<WebElement> cartBadge = driver.findElements(By.xpath("//span[@class='shopping_cart_badge']"));
		int cartBadgeCount = cartBadge.size();
		return cartBadgeCount;
	}
	
	public void clickShoppingCart () {
		shoppingCart.click();
	}
	
}
