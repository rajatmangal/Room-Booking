package booking;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BookMyRoom {
	
	//Global Variables
	static String browser = "chrome";
	static String url = "http://roombooking.surrey.sfu.ca/"; 
	static String roomUrl = "http://roombooking.surrey.åfu.ca/edit_entry.php?area=6&room=42";
	static WebDriver driver;
	static WebElement loginButton, username, password, submit;
	
	//Setting up drivers for different browsers
	
	public static WebDriver open(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("Webdriver.chrome.driver","/Users/rajatmangal/Desktop/Project/chromedriver");
			return new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("Webdriver.gecko.driver","/Users/rajatmangal/Desktop/Project/geckodriver");
			return new FirefoxDriver();	
		}
		else if(browser.equalsIgnoreCase("ie")) {
			System.setProperty("Webdriver.ie.driver","/Users/rajatmangal/Desktop/Project/IEDriverServer");
			return new InternetExplorerDriver();
		}
		return null;
	}
	
	//Initialize Driver 
	
	public static void setupDriver() {
		driver = open(browser);
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	public static void openLogin() {
		loginButton = driver.findElement(By.xpath("//*[@id=\"logon_box\"]/form/div/input[3]"));
		
		//Click on Login Button
		
		loginButton.click();
	}
	
	//Setting up login page
	
	public static void setLoginVariables() {
		username = driver.findElement(By.id("username"));
		password = driver.findElement(By.id("password"));
		submit = driver.findElement(By.name("submit"));
	}
	
	public static void login() {	
		//Fill in User name and Password
		
		username.clear();
		username.sendKeys("");
		password.clear();
		password.sendKeys("");
		
		submit.click();
	}
	
	public static void BookRoom() {
		driver.get(roomUrl);
	}
	
	public static void main(String[] args) {
		setupDriver();
		openLogin();
		setLoginVariables();
		login();
	}

}
