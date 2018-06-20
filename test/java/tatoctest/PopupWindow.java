package tatoctest;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PopupWindow {
	WebDriver driver;
	String parentWindowHandler; 
	String subWindowHandler = null;
	
	@FindBy(linkText = "Launch Popup Window")
	WebElement launchPopupWindowButton;
	
	@FindBy(linkText = "Proceed")
	WebElement proceedButton;
	
	@FindBy(xpath = "//*[@id='name']")
	WebElement formField;
	
	@FindBy(xpath = "//*[@id='submit']")
	WebElement submitButton;
	
	public PopupWindow(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		parentWindowHandler = driver.getWindowHandle(); 
	}
	
	public void proceededWithoutLaunchingPopup() {
		proceedButton.click();
	}
	
	public void subWindowsCaturingAndInputting(String formFieldString) {
		Set<String> handles = driver.getWindowHandles(); 
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
		    subWindowHandler = iterator.next().toString();
		    if(!subWindowHandler.contains(parentWindowHandler)) {
		    driver.switchTo().window(subWindowHandler);
			formField.sendKeys(formFieldString);
			submitButton.click();
			}
		    driver.switchTo().window(parentWindowHandler);
		}
		proceedButton.click();
	}
	
	public void formSubmittedBlank() {
		launchPopupWindowButton.click();
		subWindowsCaturingAndInputting("");
	}
	
	public void formSubmittedProperly() {
		launchPopupWindowButton.click();
		subWindowsCaturingAndInputting("Hello");
	}
}
