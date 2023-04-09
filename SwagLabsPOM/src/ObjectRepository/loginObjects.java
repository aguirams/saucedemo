package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginObjects {
	
	WebDriver driver;

	public loginObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "user-name")
	public WebElement username;
	
	@FindBy(id = "password")
	public WebElement password;
	
	@FindBy(id = "login-button")
	public WebElement loginButton;
	
	@FindBy(xpath = "//div[contains(@class, 'error-message-container error')]")
	public WebElement errorDisplay;
	
	// Page Actions
	
	public void enterUsername(String uname) {
		username.sendKeys(uname);
	}
	
	public void enterPassword(String pword) {
		password.sendKeys(pword);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	
	public String getErrorMessage() {
		String errorMessage = errorDisplay.getText();
		return errorMessage;
	}
	
}
