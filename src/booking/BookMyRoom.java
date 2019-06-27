package booking;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BookMyRoom {
	
	//Global Variables
	String browser = "chrome";
	String url = "http://roombooking.surrey.sfu.ca/edit_entry.php?area=6&room=43&hour=07&minute=00&year=2019&month=6&day=29"; 
	
	//Setting up drivers for different browsers
	
	public static WebDriver open(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("Webdriver.chrome.driver","/Users/rajatmangal/Desktop/SFU/Selenium/Projects/Room Booking/chromedriver");
			return new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("Webdriver.gecko.driver","/Users/rajatmangal/Desktop/SFU/Selenium/Projects/Room Booking/geckodriver");
			return new FirefoxDriver();	
		}
		else if(browser.equalsIgnoreCase("ie")) {
			System.setProperty("Webdriver.ie.driver","/Users/rajatmangal/Desktop/SFU/Selenium/Projects/Room Booking/IEDriverServer");
			return new InternetExplorerDriver();
		}
		return null;
	}
	
	public static void main(String[] args) {
		
	}

}
