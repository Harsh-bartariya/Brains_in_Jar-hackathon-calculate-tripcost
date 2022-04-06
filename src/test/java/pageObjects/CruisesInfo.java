package pageObjects;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CruisesInfo {
	
	/*********
	 *Locators
	 *********/
	
	By cruiseLink = By.linkText("Cruises");
	By cruiseLineButton = By.xpath("//span[text()='Cruise line']"); 
	By cruiseLineOption = By.xpath("//span[contains(text(),'American Queen')]");
	By cruiseShip = By.xpath("//span[text()='Cruise ship']");
	By cruiseSearch = By.xpath("//button[contains(text(),'Search')]");
	By shipOverview = By.xpath("//*[@id='ship_overview']");
	By shipDetails = By.className("dwjmk");
	
	WebDriver driver;
	
	/****************************************************
	Method Name: initDriver2
	Method Description : 
	1. Initialize Web Driver
	
	*****************************************************/
	public void initDriver2(WebDriver driver)
	{
		this.driver=driver;
	}
	
	/****************************************************
	Method Name: cruiseHomePage
	Method Description : 
	1. To click on the Cruises button
	2. To wait till the Cruises page of Trip Advisor loads
	
	Date of creation: 24-Mar-2022
	*****************************************************/
	public void clickCruisesText()
	{
		driver.findElement(cruiseLink).click();	
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	}
	/****************************************************
	Method Name: clickCruiseLine
	Method Description : 
	1. Click Cruise Line drop down
	
	*****************************************************/
	public void clickCruiseLine()
	{
		driver.findElement(cruiseLineButton).click();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	}
	/****************************************************
	Method Name: selectCruiseLine
	Method Description : 
	1. Select cruise line from available options

	*****************************************************/
	public void selectCruiseLine()
	{
		driver.findElement(cruiseLineOption).click();
	}	
	/****************************************************
	Method Name: searchCruise
	Method Description : 
	1. To Click search button

	*****************************************************/
	public void searchCruise()
	{
		driver.findElement(cruiseSearch).click();
	}	
	/****************************************************
	Method Name: switchNextTab
	Method Description : 
	1. A new tab will oen when search is clicked. This method will navigate to new tab

	*****************************************************/
	public void switchNextTab()
	{
		ArrayList<String>tabs = new ArrayList<String>(driver.getWindowHandles());
		int n = tabs.size();
		driver.switchTo().window(tabs.get(n-1));
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
	}
	/****************************************************
	Method Name: scrollTillOverview
	Method Description : 
	1. scroll down till Ship Overview is visible

	*****************************************************/
	public void scrollTillOverview()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement so = driver.findElement(shipOverview);
		js.executeScript("arguments[0].scrollIntoView(true);",so);
	}
	
	/****************************************************
	Method Name: extractShipDetails
	Method Description : 
	1. To Display/print the passengers, crew and the launched year

	*****************************************************/
	public void extractShipDetails()
	{	
		WebElement sd = driver.findElement(shipDetails);	
		System.out.println("\nShip Deatils:\n");
		System.out.println(sd.getText());
	}



}
