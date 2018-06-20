package tatoctest;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GenerateCookie {
	WebDriver driver;
	
	@FindBy(linkText = "Generate Token")
	WebElement generateTokenButton;
	
	@FindBy(css = "span#token")
	WebElement tokenField;
	
	@FindBy(linkText = "Proceed")
	WebElement proceedButton;
	
	public GenerateCookie(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void proceededWithoutGeneratingCookie() {
		proceedButton.click();
	}
	
	public void unableToGenerateCookie() {
		generateTokenButton.click();
		Cookie cookie = new Cookie("Token", "123456789");
		driver.manage().addCookie(cookie);
		proceedButton.click();
	}
	
	public void cookieGenerated() {
		generateTokenButton.click();
		String token = tokenField.getText();
		String tokenValue = token.substring(7);
		Cookie cookie = new Cookie("Token", tokenValue);
		driver.manage().addCookie(cookie);
		proceedButton.click();

	}
}
