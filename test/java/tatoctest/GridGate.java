package tatoctest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GridGate {
	WebDriver driver;
	
	@FindBy(className = "redbox")
	WebElement redBox;
	
	@FindBy(className = "greenbox")
	WebElement greenBox;
	
	public GridGate(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickRedBox() {
		redBox.click();
	}
	
	public void clickGreenBox() {
		greenBox.click();
	}
}
