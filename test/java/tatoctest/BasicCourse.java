package tatoctest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasicCourse {
	
	WebDriver driver;

	@FindBy(linkText = "Basic Course") 
	WebElement basic_Course_Button;
	
	public BasicCourse(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickButton() {
		basic_Course_Button.click();
	}
}
