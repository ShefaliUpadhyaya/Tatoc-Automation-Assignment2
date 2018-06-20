package tatoctest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BoxColor {
	WebDriver driver;
	String boxColor1, boxColor2;
	
	@FindBy(id = "main")
	WebElement mainFrame;
	
	@FindBy(xpath = "//*[@id='answer']")
	WebElement box1;
	
	@FindBy(css = "iframe#child")
	WebElement childFrame;
	
	@FindBy(xpath = "//div[@id='answer']")
	WebElement box2;
	
	@FindBy(linkText = "Proceed")
	WebElement proceedButton;
	
	@FindBy(linkText = "Repaint Box 2")
	WebElement repaintBoxButton;
	
	public BoxColor(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void fetchBox1Color() {
		driver.switchTo().frame(mainFrame);
		boxColor1 = box1.getAttribute("class");
	}
	
	public void fetchBox2Color() {
		driver.switchTo().frame(childFrame);
	    boxColor2 = box2.getAttribute("class");
	}
	
	public void colorDoesNotMatch() {
		fetchBox1Color();
		fetchBox2Color();
		driver.switchTo().parentFrame();
		if(!boxColor1.equals(boxColor2)) 
			proceedButton.click();
	}
	
	public void colorMatches() {
		fetchBox1Color();
		do {
			fetchBox2Color();
			driver.switchTo().parentFrame();
			if(boxColor1.equals(boxColor2)) {
				proceedButton.click();
				break;
			}
			else repaintBoxButton.click();
		}
		while(!boxColor1.equals(boxColor2));
	}
}
