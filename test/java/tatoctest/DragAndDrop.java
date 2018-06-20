package tatoctest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragAndDrop {
	WebDriver driver;
	
	@FindBy(xpath = "//*[@id='dragbox']")
	WebElement dragBox;
	
	@FindBy(xpath = "//*[@id='dropbox']")
	WebElement dropBox;
	
	@FindBy(linkText = "Proceed")
	WebElement proceedButton;
	
	public DragAndDrop(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void dragAndDropNotPerformed() {
		proceedButton.click();
	}
	
	public void dragAndDropPerformed() {
		Actions builder = new Actions(driver);
		builder.dragAndDrop(dragBox, dropBox).build().perform();
		proceedButton.click();
	}
}
