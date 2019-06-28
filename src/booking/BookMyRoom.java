package booking;

import java.text.SimpleDateFormat;
import java.util.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

public class BookMyRoom {
	
	//Global Variables
	static String browser = "chrome";
	static String url = "http://roombooking.surrey.sfu.ca/"; 
	static String roomUrl;
	static WebDriver driver;
	static WebElement loginButton, username, password, submit;
	static WebElement date, description, startTime, endTime, saveButton;
	static ArrayList<String> users= new ArrayList<String>();
	static WebElement errorMessage;
	
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
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(url);
	}
	
	//Will fill the array with usernames.
	
	public static void fillArrayList() {


	}
	
	//This function will open the login page.
	
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
	
	//This function will help login 
	
	public static void login() {	
		//Fill in User name and Password
		
		username.clear();
		username.sendKeys("");
		password.clear();
		password.sendKeys("");
		
		submit.click();
	}
	
	public static boolean setRoomBookingVariables() {
		driver.get(roomUrl);
		if (driver.findElements(By.cssSelector("body > h1")).size() > 0) {
			return false;
		}
		date = driver.findElement(By.id("start_datepicker"));
		description = driver.findElement(By.name("name"));
		startTime = driver.findElement(By.id("start_seconds6"));
		endTime = driver.findElement(By.id("end_seconds6"));
		saveButton = driver.findElement(By.name("save_button"));
		return true;
	}
	
	public static void changeCookies(String user) {
		driver.get(roomUrl);
		Set<Cookie> ck = driver.manage().getCookies();
		Date date = ck.iterator().next().getExpiry();
		driver.manage().deleteAllCookies();
		Cookie cookie = new Cookie("FAS_MRBS",user,"roombooking.surrey.sfu.ca","/",date);
		driver.manage().addCookie(cookie);
	}
	
	public static String getDate() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, 14);
		String requiredDate = new SimpleDateFormat("yyyy/MM/dd").format(date.getTime());
		return requiredDate;
	}
	
	public static void bookRoom(int start) {
		boolean bookingAvailable = setRoomBookingVariables();
		if(bookingAvailable) {
			String requiredDate = getDate();
			date.clear();
			date.sendKeys(requiredDate);
			description.sendKeys("group study");
			new Select(startTime).selectByIndex(start);
			if(start < 28) {
				new Select(endTime).selectByIndex(start + 4);
			} else {
				new Select(endTime).selectByIndex(start + 3);
			}
			
			saveButton.submit();
		}
	}
	
	public static void main(String[] args) {
		fillArrayList();
		setupDriver();
		openLogin();
		setLoginVariables();
		login();
		String requiredDate = getDate(); 
		roomUrl = "http://roombooking.surrey.sfu.ca/edit_entry.php?area=6&room=43&hour=07&minute=00&year=" + 
				requiredDate.substring(0, 4) + "&month=" + requiredDate.substring(5, 7) + "&day=" + requiredDate.substring(8);
		int count = 0;
		for(String user:users) {
			changeCookies(user);
			bookRoom(count);
			count += 4;
		}
		driver.close();
	}

}
