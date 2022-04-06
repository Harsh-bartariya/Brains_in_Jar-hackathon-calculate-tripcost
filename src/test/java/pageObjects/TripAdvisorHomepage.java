package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class TripAdvisorHomepage {
		
	/****************************************************
	Method Name: initDriver0
	Method Description : 
	1. Initialize Web Driver
	****************************************************/
	WebDriver driver;
	public void initDriver0(WebDriver driver)
	{
		this.driver=driver;
	}
	
	/****************************************************
	Method Name: launchTripAdvisor
	Method Description : 
	1. To Launch Trip Advisor HOMEPAGE
	*****************************************************/
	public void lauchTripAdvisor()
	{
		
		driver.get("https://www.tripadvisor.in");
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
	}		
}
