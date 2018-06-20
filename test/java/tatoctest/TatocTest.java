package tatoctest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TatocTest {
	WebDriver driver;
	BasicCourse basicCourse;
	GridGate gridGate;
	BoxColor boxColor;
	DragAndDrop dragAndDrop;
	PopupWindow popupWindow;
	GenerateCookie generateCookie;
	String url;

	@BeforeClass
	public void launchBrowser() {
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");
		basicCourse = new BasicCourse(driver);
		gridGate = new GridGate(driver);
		boxColor = new BoxColor(driver);
		dragAndDrop = new DragAndDrop(driver);
		popupWindow = new PopupWindow(driver);
		generateCookie = new GenerateCookie(driver);
    }
	
	@Test
	public void basic_Course_Button_Should_Click() {
		basicCourse.clickButton();
		Assert.assertTrue(driver.getCurrentUrl().contains("gate"));
	}
	
	@Test(dependsOnMethods = {"basic_Course_Button_Should_Click"})
	public void red_Box_Click_Should_Display_Error_Page() {
		gridGate.clickRedBox();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
		basic_Course_Button_Should_Click();
	}
	
	@Test(dependsOnMethods = {"red_Box_Click_Should_Display_Error_Page", "basic_Course_Button_Should_Click"})
	public void green_Box_Click_Should_Go_To_Next_Page() {
		gridGate.clickGreenBox();
		Assert.assertTrue(driver.getCurrentUrl().contains("dungeon"));
		url = driver.getCurrentUrl();
	}
	
	@Test(dependsOnMethods = {"green_Box_Click_Should_Go_To_Next_Page"})
	public void box_Colors_Do_Not_Match_Should_Display_Error_Page() {
		boxColor.colorDoesNotMatch();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"green_Box_Click_Should_Go_To_Next_Page", "box_Colors_Do_Not_Match_Should_Display_Error_Page"})
	public void box_Colors_Match_Should_Go_To_Next_Page() {
		driver.get(url);
		boxColor.colorMatches();
		Assert.assertTrue(driver.getCurrentUrl().contains("drag"));
		url = driver.getCurrentUrl();
	}
	
	@Test(dependsOnMethods = {"box_Colors_Match_Should_Go_To_Next_Page"})
	public void drag_Me_Box_Not_In_DropBox_Goes_To_Error_Page() {
		dragAndDrop.dragAndDropNotPerformed();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"drag_Me_Box_Not_In_DropBox_Goes_To_Error_Page", "box_Colors_Match_Should_Go_To_Next_Page"})
	public void drag_Me_Box_In_DropBox_Goes_To_Next_Page() {
		driver.get(url);
		dragAndDrop.dragAndDropPerformed();
		Assert.assertTrue(driver.getCurrentUrl().contains("windows"));
		url = driver.getCurrentUrl();
	}
	
	@Test(dependsOnMethods = {"drag_Me_Box_In_DropBox_Goes_To_Next_Page"})
	public void clicked_Proceed_Without_Launching_Popup_Window_Goes_To_Error_Page() {
		popupWindow.proceededWithoutLaunchingPopup();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"drag_Me_Box_In_DropBox_Goes_To_Next_Page", "clicked_Proceed_Without_Launching_Popup_Window_Goes_To_Error_Page"})
	public void launched_Popup_Window_But_Form_Submitted_Blank_Goes_To_Error_Page() {
		driver.get(url);
		popupWindow.formSubmittedBlank();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"drag_Me_Box_In_DropBox_Goes_To_Next_Page", "launched_Popup_Window_But_Form_Submitted_Blank_Goes_To_Error_Page"})
	public void launched_Popup_Window_And_Form_Submitted_Properly_Goes_To_Next_Page() {
		driver.get(url);
		popupWindow.formSubmittedProperly();
		Assert.assertTrue(driver.getCurrentUrl().contains("cookie"));
		url = driver.getCurrentUrl();
	}
	
	@Test(dependsOnMethods = {"launched_Popup_Window_And_Form_Submitted_Properly_Goes_To_Next_Page"})
	public void clicked_Proceed_Without_Generating_Token_Goes_To_Error_Page() {
		generateCookie.proceededWithoutGeneratingCookie();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"launched_Popup_Window_And_Form_Submitted_Properly_Goes_To_Next_Page", "clicked_Proceed_Without_Generating_Token_Goes_To_Error_Page"})
	public void cookie_Not_Generated_Properly() {
		driver.get(url);
		generateCookie.unableToGenerateCookie();
		Assert.assertTrue(driver.getCurrentUrl().contains("error"));
	}
	
	@Test(dependsOnMethods = {"launched_Popup_Window_And_Form_Submitted_Properly_Goes_To_Next_Page", "cookie_Not_Generated_Properly"})
	public void cookie_Generated_Properly() {
		driver.get(url);
		generateCookie.cookieGenerated();
		Assert.assertTrue(driver.getCurrentUrl().contains("end"));
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
