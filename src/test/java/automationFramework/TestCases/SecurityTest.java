package automationFramework.TestCases;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
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

public class SecurityTest {

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

	
	//T141 - Test that application masks cc number when displayed
	@Test(priority=1 , enabled=false)
	public void creditCardDisplayTest() throws Exception{
		HomePage homePage = new HomePage(driver);
		homePage.getLandingPage();
		homePage.clickSignIn(driver);
		SignInPage sPage = new SignInPage(driver);
		sPage.enterEmail(driver, "ray.khorrami1@cubic.com");
		sPage.enterPasswd(driver, "Test1234");
		sPage.clickSignInAccount(driver);
		AccountPage aPage = new AccountPage(driver);
		aPage.clickMyAccount(driver);
		
		//Assertions on account page
		Assert.assertEquals(aPage.getName(driver), "ray khorrami");
		Log.info("Actual results " + aPage.getName(driver) + " matches " + "ray khorrami");	
		Assert.assertEquals(aPage.getEmail(driver), "ray.khorrami1@cubic.com");
		Log.info("Actual results " + aPage.getEmail(driver) + " matches " + "ray.khorrami1@cubic.com");	
		Assert.assertEquals(aPage.getPaymentType(driver), Global.PAYMENT_TYPE);
		Log.info("Actual results " + aPage.getPaymentType(driver) + " matches " + Global.PAYMENT_TYPE);	
		Assert.assertEquals(aPage.getAutoloadPaymentType(driver), Global.PAYMENT_TYPE);
		Log.info("Actual results " + aPage.getAutoloadPaymentType(driver) + " matches " + Global.PAYMENT_TYPE);	
		
		//Assertion on manage payment type page
		aPage.clickManagePaymentType(driver);
		ManagePaymentPage mPage = new ManagePaymentPage(driver);
		Assert.assertEquals(mPage.getPaymentType(driver), Global.PAYMENT_TYPE);
		mPage.clickBack(driver);
		Utils.waitTime(3000);
		aPage.clickManageAutoloadPaymentType(driver);
		Assert.assertEquals(mPage.getPaymentType(driver), Global.PAYMENT_TYPE);
		mPage.clickBack(driver);
		Utils.waitTime(3000);
		aPage.clickSignOut(driver);
	}
	
	//T119 - Test for clickjacking 
	    @Test(priority=1 , enabled=true)
		public void clickJackTest() throws Exception{
			HomePage homePage = new HomePage(driver);
			homePage.getLandingPage();
			homePage.clickSignIn(driver);
			SignInPage sPage = new SignInPage(driver);
			sPage.enterEmail(driver, "ray.khorrami1@cubic.com");
			sPage.enterPasswd(driver, "Test1234");
			sPage.clickSignInAccount(driver);
			AccountPage aPage = new AccountPage(driver);
			aPage.clickMyAccount(driver);
			
			//clickjack 
			aPage.clickJackLogo(driver);
			
			//Assertions on card info page
			CardInfoPage cPage = new CardInfoPage(driver);
			Utils.waitTime(3000);
			Assert.assertEquals(cPage.getCardInfoPanel(driver), "Compass Card information");
			Log.info("Actual results " + cPage.getCardInfoPanel(driver) + " matches " + "Compass Card information");
			aPage.clickSignOut(driver);
		}
	
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}

}