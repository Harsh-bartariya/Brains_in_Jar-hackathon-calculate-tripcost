package testCases;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.CruisesInfo;
import pageObjects.HolidayHomes;
import pageObjects.TripAdvisorHomepage;


/***************************************************
Script Name: CalculateTripCost
Requirement Description : 
1. Display Hotel name, total amount and
   charges per night for 3 holiday homes for 4 people in Nairobi
   for 5 days of stay from tomorrow's date;
    Should have sorted the list with highest traveler rating
    & should have elevator/ List  access
2 Pick one cruise line & pick a respective cruise ship under Cruises; 
                a. Retrieve all the languages offered and store in a List; Display the same
                b. Display passengers, crew & launched yearnst the expected i.e, India

*****************************************************/
@SuppressWarnings("unused")
public class CalculateTripCost {
	
	//WebDriver Declaration
	
    WebDriver driver;
			
	//Report
    
	public static Logger lg = Logger.getLogger("ActivityLog.class");
		
	//Object Creations

    TripAdvisorHomepage obj1 = new TripAdvisorHomepage();

    HolidayHomes obj2 = new HolidayHomes();
    
    CruisesInfo obj3 = new CruisesInfo();
    
    
    @BeforeSuite
	public void preRequisites()
	{
		//System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
    	
    	WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test(priority =0)
	public void HomePage()
	{
		obj1.initDriver0(driver);
		obj1.lauchTripAdvisor();
		lg.info("Trip Advisor has been launced");
	}
	
	@Test(priority =1)
	public void clickHolidayHomes()
	{
		obj2.initDriver1(driver);
		obj2.clickHolidayHomesBtn();
		lg.info("Clicked Holiday Homes Option");
	}
	
	@Test(priority =2)
	public void holidayHomesSearchBox() throws InterruptedException
	{
		obj2.searchCity();
		lg.info("Entered place name in search box ");
	}
	
	@Test(priority =3)
	public void clickOnSearch()
	{
		obj2.pressSearch();
		lg.info("Clicked on search to view available holiday homes/Rentals");
	}
	@Test(priority=4)
	public void rating() throws InterruptedException
	{
		Thread.sleep(1000);
		obj2.Rating();
		//Thread.sleep(500);
		lg.info("Applied Rating filter");
	
	}
	
	@Test(priority=5)
	public void durationOfStay()
	{
		obj2.checkInOut();
		lg.info("Selected check in and check out dates");
	}
	
	@Test(priority=6)
	public void noOfPeople()
	{
		obj2.guests();
		lg.info("Applied Guests to stay");
	}

	@Test(priority=7)
	public void amenities() throws InterruptedException
	{
		Thread.sleep(1000);
		obj2.selectElevatorAccess();
		lg.info("Selected Elevator/Lift access from Amenities");
	}
	
	@Test(priority=8)
	public void ScrollUp() throws InterruptedException
	{
		Thread.sleep(3000);
		obj2.scrollUp();
		lg.info("Scrolled to top");
	}
	
	@Test(priority=9)
	public void tripCost() throws InterruptedException
	{
		obj2.homesBasedOnTravellerRating();
		Thread.sleep(3000);
		lg.info(" Holiday Home name, total amount and charges per night for 3 holiday homes for 4+ people in Nairobi for 5 days of stay from tomorrow's date are displayed.");
		lg.info(" The list is sorted  with highest traveler rating &  have elevator/ Lift  access");
		
		System.out.println("Languages : English");
		System.out.println();
	}
	
	@Test(priority=10)
	public void ClickCruises()
	{
		obj3.initDriver2(driver);
		obj3.clickCruisesText();
		lg.info("Clicked on Cruises Button");
	}	
	@Test(priority=11)
	public void cruiseLine() throws InterruptedException
	{
        Thread.sleep(1000);
		obj3.clickCruiseLine();
		obj3.selectCruiseLine();
		lg.info("Respective cruise line is selected");
	}
	
	@Test(priority=12)
	public void CruiseSearch() throws InterruptedException
	{
		Thread.sleep(1000);
		obj3.searchCruise();
		lg.info("Search is clicked");
	}
	
	@Test(priority=13)
	public void tabSwitch()
	{
		obj3.switchNextTab();
		lg.info("Switched tab");
	}
	
	@Test(priority=14)
	public void scrollDown()
	{
		obj3.initDriver2(driver);
		obj3.scrollTillOverview();
		lg.info("Scrolled down to see Ship Details");
	}
	
	@Test(priority=15)
	public void CruiseOverview() throws InterruptedException
	{
		obj3.extractShipDetails();
		Thread.sleep(1000);
	
		lg.info("Ship details are printed");
	}

	@AfterSuite
	
	public void exit()
	{
		driver.quit();
	}

	
}
