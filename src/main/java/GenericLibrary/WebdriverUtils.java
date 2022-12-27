package GenericLibrary;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class WebdriverUtils {
  // webdriver methods 
	/**
	 * This method will maximize the window
	 * @cvk
	 */
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	/*
	 * This method will Wait for 20 sec to load the page
	 */
	public void waitforPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	/*
	 * This method will wait element to be click able
	 */
	public void waitForElementTobeClickable(WebDriver driver,WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	/*
	 *  This method will wait element to be visible
	 */
	public void waitForElementTobeVisible(WebDriver driver,WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	/*
	 * This method will select data from dropDown using index
	 */
	public void select(WebElement ele,int index) {
		Select sel = new Select(ele);
		sel.selectByIndex(index);
	}
	/*
	 * This method will select data from dropDown using Text
	 */
	public void select(WebElement ele,String text) {
		Select sel = new Select(ele);
		sel.selectByVisibleText(text);
	}
	/*
	 * This method will select data from dropDown using Value
	 */
	public void select(String Value,WebElement ele) {
		Select sel = new Select(ele);
		sel.selectByValue(Value);
	}
	/*
	 * This method will perform MousHover actions
	 */
	public void mouseHover(WebElement ele,WebDriver driver) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}
	/*
	 * This method will perform MousHover actions
	 */
	public void dragAndDrop(WebElement src,WebElement target,WebDriver driver) {
		Actions act = new Actions(driver);
		act.dragAndDrop(src, target).perform();
	}
	/**
	 * This method will double click on element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element) 
	{
		Actions act = new Actions(driver);
		act.doubleClick(element);
	}
	
	/**
	 * This method will double on webpage
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.doubleClick().perform();
	}
	
	/**
	 *This method will right click on webpage
	 * @param driver
	 */
	public void rightClick(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.contextClick().perform();
	}
	
	/**
	 * This method will right click on element
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will press enter key
	 * @param driver
	 */
	public void enterKeyPress(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}
	
	/**
	 * This method will press enter key
	 * @throws Throwable
	 */
	public void enterKey() throws Throwable
	{
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will release enter key
	 * @throws Throwable
	 */
	public void enterRelease() throws Throwable
	{
		Robot rb = new Robot();
		rb.keyRelease(KeyEvent.VK_ENTER);
	}
	
	/**
	 * This method will switch the frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method will switch the frame based on name or ID
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
	}
	
	/**
	 * This method will switch the frame based on address of the element
	 * @param driver
	 * @param address
	 */
	public void switchToFrame(WebDriver driver, WebElement address)
	{
		driver.switchTo().frame(address);
	}
	
	/**
	 * This method will accept alert pop up
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel the alert pop up
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will switch to window depending on partial window title
	 * @param driver
	 * @param partialWinTitle
	 */
	public void switchToWindow(WebDriver driver, String partialWinTitle)
	{
		//Step 1: use getWindowHandles to capture all window ids
		Set<String> windows = driver.getWindowHandles();
		
		//Step 2: iterate thru the windows
		Iterator<String> it = windows.iterator();
		
		//Step 3: Check whether there is next window
		while(it.hasNext())
		{
			//Step 4: capture current window id
			String winId = it.next();
			
			//Step 5: switch to current window and capture title
			String currentWinTitle = driver.switchTo().window(winId).getTitle();
			
			//Step 6: check whether the current winodw is expected
			if(currentWinTitle.contains(partialWinTitle))
			{
				break;
			}
			
		}
		
	}
	
	/**
	 * This method will take screenShot and store it in folder called as Screenshot
	 * @param driver
	 * @param screenShotName
	 * @throws Throwable
	 */
	public String getScreenShot(WebDriver driver,String screenShotName) throws Throwable
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src= ts.getScreenshotAs(OutputType.FILE);
		String path =".\\Screenshots\\"+screenShotName+".png";
		File dst = new File(path);
		FileUtils.copyFile(src, dst);
		
		return dst.getAbsolutePath();
	}
	
	/**
	 * This method perform random scroll
	 * @param driver
	 */
	public void scrollAction(WebDriver driver)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
	}
	
	/**
	 * This method will scroll until the specified element is found
	 * @param driver
	 * @param element
	 */
	public void scrollAction(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int y = element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
		//js.executeScript("argument[0].scrollIntoView()",element);
	}	
}
