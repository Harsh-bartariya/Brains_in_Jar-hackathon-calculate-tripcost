package pageObjects;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.ReadExcelDataFile;


public class HolidayHomes {

	/*********
	 *Locators
	 *********/
	
	By hhClick = By.xpath("//span[contains(text(),'Holiday Homes')]");
	By location = By.xpath("//div[@class='weiIG Z0 Wh eoyKC fRhqZ eNLxe']//input[@title='Search']");
	By submitAndSearch = By.xpath("//*[@id=\"typeahead_results\"]/a[1]");
	By sortDropDown = By.xpath("//span[text()='Tripadvisor Sort']");
	By rating = By.xpath("//span[text()='Traveller Rating']");
	By showAmen = By.xpath("//div[contains(text(),'Other outdoor')]/following::div[1]");
	By lift = By.xpath("//div[contains(text(),'Elevator')]");
	By chkincal = By.xpath("//div[text()='Check-in']");
	By  guests = By.className("fXQdL");
	By plusGuest = By.xpath("//div[text()='guests']/following::span[3]");
	By applyGuest = By.xpath("//button[text()='Apply']");
	By rentalName = By.xpath("//*[@class='fLhRg b S7 W o q']");
	By totalCost = By.xpath("//div[@class='bWUxM']");




	WebDriver driver;
	
	//Creating object to read data from excel
	ReadExcelDataFile excel = new ReadExcelDataFile("./TestData/TestData.xlsx");
	
	/****************************************************
	Method Name: initDriver1
	Method Description : 
	1. Initialize Web Driver

	*****************************************************/
	public void initDriver1(WebDriver driver)
	{
		this.driver=driver;
	}
	
	/****************************************************
	Method Name: clickHolidayHomes
	Method Description : 
	1. To Click Holiday Homes

	****************************************************/
	
	public void clickHolidayHomesBtn()
	{

		driver.findElement(hhClick).click();
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
	}
	
	/****************************************************
	Method Name: searchCity
	Method Description : 
	1. Enter Nairobi in search box using excel
	 * @throws InterruptedException 

	*****************************************************/
	public void searchCity() throws InterruptedException
	{
		driver.findElement(location).sendKeys(excel.getData(0, 0, 0));
			Thread.sleep(1000);
	}
	
	/****************************************************
	Method Name:pressSearch
	Method Description : 
	1. To click Search Button

	*****************************************************/
	public void pressSearch()
	{
		driver.findElement(submitAndSearch).click();
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
	}
	
	/****************************************************
	Method Name: Rating
	Method Description : 
	1. To select the Required Sorting
	
	*****************************************************/
	public void Rating()
	{
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(sortDropDown).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		driver.findElement(rating).click();
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
	}	
	
	/****************************************************
	Method Name: checkInOut
	Method Description : 
	1. To get check in and check out dates from Java date class and apply them.

	*****************************************************/
	public void checkInOut()
	{
		
		Date today = new Date();
		Date tomorrow = new Date(today.getTime()+ (1000*60*60*24));
		
		String chkin = (new SimpleDateFormat("EEE MMM dd yyyy").format(tomorrow)).toString();
		System.out.println("\nCheck in: "+chkin);
		
		Date sixlater = new Date(today.getTime()+ (6*1000*60*60*24));
		
		String chkout = (new SimpleDateFormat("EEE MMM dd yyyy").format(sixlater)).toString();
		System.out.println("Check out: "+chkout+"\n");
		
		By chkinDate = By.xpath("//div[@aria-label='"+chkin+"']");
		By chkoutDate = By.xpath("//div[@aria-label='"+chkout+"']");
		
		//Click on calender icon to view calender
		driver.findElement(chkincal).click();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		//Click Date for checkin
		driver.findElement(chkinDate).click();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		//Click Date for Checkout
		driver.findElement(chkoutDate).click();
	}

	/****************************************************
	Method Name: guests
	Method Description : 
	1. To Apply the required guests

	*****************************************************/
	public void guests()
	{
		driver.findElement(guests).click();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		driver.findElement(plusGuest).click(); // Guests will increase to 3+
		driver.findElement(plusGuest).click(); // Gustes increased to 4+
		driver.findElement(applyGuest).click();
	}
	/****************************************************
	Method Name: selectElevatorAccess
	Method Description : 
	1. To Select Elevator/Lift access from Ameneties

	*****************************************************/
	public void selectElevatorAccess()
	{
		
		driver.findElement(showAmen).click();
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
		driver.findElement(lift).click();
		
	}
	/****************************************************
	Method Name: scrollUp
	Method Description : 
	1. To Scroll up the web page
	
	*****************************************************/
	public void scrollUp()
	{
		
		JavascriptExecutor js1 = (JavascriptExecutor)driver;
		js1.executeScript("window.scrollBy(0,-500)");
		
		
	}
	/****************************************************
	Method Name: homesBasedOnTravellerRating()
	Method Description : 
	1. To display holiday home names , total cost and charges per night.

	*****************************************************/
	public void homesBasedOnTravellerRating()
	{
		// List of Holiday home names
		List <WebElement> hhName = driver.findElements(By.xpath("//h2[@class='fLhRg b S7 W o q']"));
		//List of charges
		List <WebElement> charges = driver.findElements(totalCost);
		
		
		System.out.println("\nHoliday Homes Details:\n---------------------");
		
		//After applying all filters and conditions, printing top 3
		for(int j=0;j<=2;j++)
		{
			System.out.println(j+1+"\n");
			System.out.println("Rental name = "+hhName.get(j).getText()+"\n");
			System.out.println("Cost = "+charges.get(j).getText()+"\n\n");
		}

	}
}
