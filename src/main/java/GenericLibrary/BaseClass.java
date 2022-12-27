package GenericLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
    //public static WebDriver driver;
	public PropertyFileUtility propUtility=new PropertyFileUtility();
	public WebdriverUtils webUtils=new WebdriverUtils();
	public JavaUtility jlib=new JavaUtility();
	public WebDriver driver;
	public String Baseurl=propUtility.getBaseURL();
	public static WebDriver adriver;
	
	//Only if connection with DataBase is required
//	@BeforeSuite(groups = {"regression","smoke"})
//	public void connectingToDatabase() {
//		Reporter.log("connected to database",true);
//	}
	@BeforeClass(groups = {"regression","smoke"})
	public void startUp() {
		String browser = propUtility.getBrowser();	
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			System.setProperty("webdriver.chrome.driver",propUtility.getChromePath());
		}
		else if(browser.equalsIgnoreCase("firefox")){
			driver=new FirefoxDriver();
			System.setProperty("webdriver.firefox.driver",propUtility.getFirefoxPath());
		}
		else {
			Reporter.log("No longer browser are involved");
		}
		adriver=driver;
		webUtils.maximizeWindow(adriver);
		webUtils.waitforPageToLoad(adriver);
		driver.get(Baseurl);
		Reporter.log("Browser launch successfuly");
		
	}
	@AfterClass
	public void closeBrowser() {
		driver.quit();
		Reporter.log("Browser successfuly closed");
	}
//	@AfterSuite
//	public void closeDB() {
//		
//	}
	
	
	
	
	}
