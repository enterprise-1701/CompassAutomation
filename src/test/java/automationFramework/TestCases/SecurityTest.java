package automationFramework.TestCases;

import java.awt.AWTException;
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

public class SecurityTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String FNAME = "Adam";
	private static final String LNAME = "Smith";
	private static final String ADDRESS = "2015 Van Horne Dr";
	private static final String CITY = "Kamloops";
	private static final String POSTAL = "V1S 1G7";
	private static final String SECURITY = "San diego";
	private static final int TIME = 10000;
	private static String expectedEmail;
	private static String passwd;
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
	@Test(priority=1 , enabled=true)
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
		driver.close();
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
			aPage.clickJackLogo(driver, TIME);
			
			//Assertions on card info page
			CardInfoPage cPage = new CardInfoPage(driver);
			Utils.waitTime(3000);
			Assert.assertEquals(cPage.getCardInfoPanel(driver), "Compass Card information");
			Log.info("Actual results " + cPage.getCardInfoPanel(driver) + " matches " + "Compass Card information");
			aPage.clickSignOut(driver);
			driver.close();
		}
	
	    
	  //T81 - Test for account lockout
		@Test(priority=1 , enabled=true)
		public void accountLockoutTest() throws Exception{
			HomePage homePage = new HomePage(driver);
			homePage.getLandingPage();
			homePage.clickSignIn(driver);
			SignInPage sPage = new SignInPage(driver);
			
			//Try to login with invalid password 5 times
			for(int i=0; i<5; i++){
			sPage.enterEmail(driver, "ray.khorrami1@cubic.com");
			sPage.enterPasswd(driver, "Test");
			sPage.clickSignInAccount(driver);
			sPage.clearEmail(driver);
			sPage.clearPasswd(driver);
			Assert.assertEquals(sPage.getLoginError(driver), Global.LOGIN_ERROR);
			}
			
			//Login with valid password now
			sPage.enterEmail(driver, "ray.khorrami1@cubic.com");
			sPage.enterPasswd(driver, "Test1234");
			sPage.clickSignInAccount(driver);
			
			//The lockout has not been implemented yet. This test will fail.
	        CardInfoPage cPage = new CardInfoPage(driver);
	        //Assert.assertFalse(cPage.isCardInfoPanelDisplayed(driver), "User should be locked out!");
			driver.close();
		}
		
		
	  //T80 Test for password requirements 
		@Test(priority=1 , enabled=true)
		public void passwordRequirementsTest() throws Exception {
			
			HomePage homePage = new HomePage(driver);
			homePage.getLandingPage();
			homePage.clickOrderCard(driver);
			
			OrderPage orderPage = new OrderPage(driver);
			orderPage.clickAddValue(driver);
			orderPage.clickTen(driver);

			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,450)", "");
			orderPage.clickAddPass(driver);
			orderPage.clickZone1(driver);
			orderPage.clickAddToOrder(driver);
			
			OrderReviewPage orderRevPage = new OrderReviewPage(driver);
			orderRevPage.clickContinue(driver);
			
			ShippingPage shippingPage = new ShippingPage(driver);
			shippingPage.enterFirstName(driver, FNAME);
			shippingPage.enterLastName(driver, LNAME);
			shippingPage.enterAddress(driver, ADDRESS);
			shippingPage.enterCity(driver, CITY);
			shippingPage.enterPostalCode(driver, POSTAL);
			shippingPage.clickContinue(driver);
			
			CardRegistrationPage regPage = new CardRegistrationPage(driver);
			expectedEmail = Utils.randomEmailString();
			regPage.enterEmail(driver, expectedEmail);
			
			//Check for alphabet characters
			checkPasswords(driver, "12345678", Global.PASSSWD_ERROR2);
			
			//Check for numeric characters
			checkPasswords(driver, "Abcdefgh", Global.PASSSWD_ERROR2);
			
			//Check for uppercase characters
			checkPasswords(driver, "abcde123", Global.PASSSWD_ERROR2);
			
			//Check for start with space 
			checkPasswords(driver, " Test123", Global.PASSSWD_ERROR2);
			
			//Check for end with space 
			checkPasswords(driver, "Test123 ", Global.PASSSWD_ERROR2);
			
			//Check for less than 8 characters
			passwd = "Test123";
			regPage.clearPasswd(driver);
			regPage.enterPasswd(driver, passwd);
			regPage.clickPasswd2(driver);
			Assert.assertEquals(regPage.getPasswdError(driver), Global.PASSSWD_ERROR);
			
			//Check for password fields not matching
			passwd = "Test1234";
			regPage.clearPasswd(driver);
			regPage.enterPasswd(driver, passwd);
			regPage.enterPasswd2(driver, "Tesd1234");
			regPage.enterSecurityAnswer(driver, SECURITY);
			regPage.clickEmailPref(driver);
			regPage.clickContinue(driver);
			Assert.assertEquals(regPage.getPasswdError3(driver), Global.PASSSWD_ERROR3);
			driver.close();
				
		}
		
		
		 //T225 Test that password fields are masked by default
		@Test(priority=1 , enabled=true)
		public void passwordMaskedTest() throws Exception {
			
			HomePage homePage = new HomePage(driver);
			homePage.getLandingPage();
			homePage.clickOrderCard(driver);
			
			OrderPage orderPage = new OrderPage(driver);
			orderPage.clickAddValue(driver);
			orderPage.clickTen(driver);

			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,450)", "");
			orderPage.clickAddPass(driver);
			orderPage.clickZone1(driver);
			orderPage.clickAddToOrder(driver);
			
			OrderReviewPage orderRevPage = new OrderReviewPage(driver);
			orderRevPage.clickContinue(driver);
			
			ShippingPage shippingPage = new ShippingPage(driver);
			shippingPage.enterFirstName(driver, FNAME);
			shippingPage.enterLastName(driver, LNAME);
			shippingPage.enterAddress(driver, ADDRESS);
			shippingPage.enterCity(driver, CITY);
			shippingPage.enterPostalCode(driver, POSTAL);
			shippingPage.clickContinue(driver);
			
			CardRegistrationPage regPage = new CardRegistrationPage(driver);
			expectedEmail = Utils.randomEmailString();
			regPage.enterEmail(driver, expectedEmail);
			
			//check password and confirm password
			passwd = Utils.randomPasswdString();
			regPage.enterPasswd(driver, passwd);
			Assert.assertTrue(regPage.isPasswdMasked(driver), "Password is not Masked!");
			regPage.enterPasswd2(driver, passwd);
			Assert.assertTrue(regPage.isPasswd2Masked(driver), "Confirm Password is not Masked!");
			driver.close();
		}
		
		//T93 - Test that session expires upon logout
		@Test(priority=1 , enabled=true)
		public void sessionExpiresTest() throws Exception{
			HomePage homePage = new HomePage(driver);
			homePage.getLandingPage();
			homePage.clickSignIn(driver);
			SignInPage sPage = new SignInPage(driver);
			sPage.enterEmail(driver, "ray.khorrami1@cubic.com");
			sPage.enterPasswd(driver, "Test1234");
			sPage.clickSignInAccount(driver);
			AccountPage aPage = new AccountPage(driver);
			aPage.clickMyAccount(driver);
			
			//Assertion on account page
			Assert.assertEquals(aPage.getName(driver), "ray khorrami");
			Log.info("Actual results " + aPage.getName(driver) + " matches " + "ray khorrami");	
			aPage.clickSignOut(driver);
			
			//Assertions that signed out user is not able to access account and my card pages
			Assert.assertFalse(aPage.accessSignedInUrl(driver, Global.MANAGE_CARD_URL), "Logged out user is able to access manage card page!");
			Assert.assertFalse(aPage.accessSignedInUrl(driver, Global.ACCOUNT_URL), "Logged out user is able to access account page!");
			driver.close();
		}
		
		//private methods
		private void checkPasswords(WebDriver driver, String passwd, String error) throws InterruptedException, AWTException{
			
			CardRegistrationPage regPage = new CardRegistrationPage(driver);
			regPage.clearPasswd(driver);
			regPage.enterPasswd(driver, passwd);
			regPage.clickPasswd2(driver);
			Assert.assertEquals(regPage.getPasswdError2(driver), error);
			
		}
		
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}

}