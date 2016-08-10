package automationFramework.Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import automationFramework.Utilities.Global;

public final class Utils {

	public static WebDriver driver = null;

	public static void waitForElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitTime(long milliseconds) throws InterruptedException {
		Thread.sleep(milliseconds);
	}

	public static void isPageLoaded(WebDriver driver, String title) {
		Assert.assertTrue(driver.getTitle().equals(title));
	}

	public static WebDriver openBrowser(String browser) {

		try {
			if (browser.equals("firefox.exe")) {
				
				FirefoxProfile prof = new FirefoxProfile();
				prof.setPreference("browser.startup.homepage_override.mstone", "ignore");
				prof.setPreference("startup.homepage_welcome_url.additional",  "about:blank");
				driver = new FirefoxDriver(prof);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				Reporter.log("firefox driver instantiated");
			
			} else if (browser.equals("chrome.exe")) {
				System.setProperty("webdriver.chrome.driver",
						"C:/ChromeDriver/chromedriver.exe");

				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));
				ChromeOptions options = new ChromeOptions();
				options.addArguments("test-type");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver();
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				Log.info("chrome driver instantiated");		

			}
			

		} catch (Exception e) {
			Reporter.log("Utils.openBrowser failed");
		}
		return driver;
	}
	
	
	//Creating random email and password
	public static String randomEmailString(){
		String randomEmail = generateRandomString() + Global.GMAIL;
		return randomEmail;
	}
	
	public static String randomPasswdString(){
		String randomPasswd = generateRandomString() + "1X!";
		return randomPasswd;
	}
	
	//Generating random string
	private static String generateRandomString(){
		StringBuffer randStr = new StringBuffer();
		for(int i=0; i<12; i++){
			int number = getRandomNumber();
			char ch = Global.CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}
	
	//Generate random number
	private static int getRandomNumber(){
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(Global.CHAR_LIST.length());
		if(randomInt - 1 == -1){
			return randomInt;
		} else{
			return randomInt -1;
		}
	}
	

	// capturing screenshots of the scripts
	public static void getScreenShot() throws Exception {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile,
					new File("C:/Automation/ScreenShots/" + System.currentTimeMillis() + "_screenshot.png"));
			Reporter.log("Screenshot Captured");
		} catch (IOException e) {
			Reporter.log("Failed to capture screenshot");
		}
	}
    
	// handling multiple windows and selecting the right one
	public static void handleMultipleWindows(WebDriver driver, String windowTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getTitle().contains(windowTitle)) {
				return;
			}
		}
	}

}