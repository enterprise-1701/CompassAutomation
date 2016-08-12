package automationFramework.TestCases;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automationFramework.PageObjects.*;
import automationFramework.Utilities.*;

public class SecurityTest2 {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	static WebDriver driver;
	static String browser;
	
	@Parameters("browser")

	@BeforeMethod
	public void setUp(String browser) throws InterruptedException {

		// create console and file logging
		Logging.setLogConsole();
		Logging.setLogFile();
		Log.info("Setup Started");
		Log.info("Current OS: " + WindowsUtils.readStringRegistryValue(Global.OS));
		Log.info("Current Browser: " + browser);
		driver = Utils.openBrowser(browser);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Log.info("Setup Completed");
	}

	    //T104 - Test that site does not reveal detailed information in error pages
		@Test(priority=1 , enabled=true)
		public void sessionExpiresTest() throws Exception{
			HomePage homePage = new HomePage(driver);
			homePage.getLandingPage();
			homePage.clickSignIn(driver);
			SignInPage sPage = new SignInPage(driver);
			sPage.enterEmail(driver, "ray.khorrami1@cubic.com");
			sPage.enterPasswd(driver, "Test1234");
			sPage.clickSignInAccount(driver);
			List<String> data = new ArrayList<String>(Arrays.asList("0164 0605 7680 1762 9448", "01640605768017629448", "Visa - 1111", "Visa-1111", "4111111111111111", "visa", "1111", "@cubic.com", "ray khorrami", "password", "Card Information", "card information", "Telephone PIN", "telephone pin", "PIN", "pin", "1234"));
			boolean dataDisplayed = Security.checkPageContent(driver, Global.ERROR_PAGE, data);
			Assert.assertFalse(dataDisplayed, "Data is being displayed on the Error page!");
		}
		
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}

}