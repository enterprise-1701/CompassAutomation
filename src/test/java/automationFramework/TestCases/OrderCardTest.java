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

public class OrderCardTest {

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private static final String FNAME = "Adam";
	private static final String LNAME = "Smith";
	private static final String FULLNAME = " Adam  Smith";
	private static final String FULNAME = "Adam Smith";
	private static final String ADDRESS = "2015 Van Horne Dr";
	private static final String CITY = "Kamloops";
	private static final String POSTAL = "V1S 1G7";
	private static final String SECURITY = "San diego";
	private static final String PHONE = "8588992999";
	private static final String CARD_VALUE = "$107.00";
	private static final String STORED_VALUE = "$10.00";
	private static final String ZONE_PASS = "$91.00";
	private static final String CARD_DEPOSIT = "$6.00";
	private static final String SHIPPING = "$0.00";
	private static final String TOTAL = "$107.00";
	private static final int SUBSTRING = 27;
	private static final String SUCCESS = "Thanks, your feedback is appreciated!";
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

	
	//Order Card Test Case
	@Test(priority=1)
	public void orderCardTest() throws Exception {
		
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
		passwd = Utils.randomPasswdString();
		
		regPage.enterPasswd(driver, passwd);
		regPage.enterPasswd2(driver, passwd);
		regPage.enterSecurityAnswer(driver, SECURITY);
		regPage.clickEmailPref(driver);
		regPage.clickContinue(driver);
		
		PaymentPage payPage = new PaymentPage(driver);
		payPage.enterName(driver, LNAME);
		payPage.enterCC(driver, Global.CC);
		payPage.enterExpDate(driver, Global.EXPDATE);
		payPage.enterExpYear(driver, Global.EXPYEAR);
		payPage.enterCvv(driver, Global.CVV);
		payPage.clickMailingInfo(driver);
		Utils.waitTime(5000);
		payPage.enterPhone(driver, PHONE);
		payPage.clickContinue(driver);
		
		ReviewPage reviewPage = new ReviewPage(driver);
		reviewPage.clickAuthorize(driver);
		ThankYouPage thankPage = new ThankYouPage(driver);
			
		
			Log.info("Starting Test Assertions");
			Assert.assertEquals(parseEmail(thankPage), expectedEmail);
			Log.info("Actual results " + parseEmail(thankPage) + " matches " +  expectedEmail);
			Assert.assertTrue(thankPage.isReceiptDisplayed(driver), "Receipt is Missing!");
			Log.info("Receipt Returned: " + thankPage.isReceiptDisplayed(driver));
			Assert.assertTrue(thankPage.isOrderNumberDisplayed(driver), "Order Number is Missing!");
			Log.info("Order Number Returned: " + thankPage.isOrderNumberDisplayed(driver));
			Assert.assertTrue(thankPage.isOrderDateDisplayed(driver), "Order Date is Missing!");
			Log.info("Order Date Returned: " + thankPage.isOrderDateDisplayed(driver));
			Assert.assertTrue(thankPage.isAuthorizationDisplayed(driver), "Authorization is Missing!");
			Log.info("Authorization Returned: " + thankPage.isAuthorizationDisplayed(driver));
			Assert.assertEquals(thankPage.getCardValue(driver),  CARD_VALUE);
			
			
			Log.info("Actual results " + thankPage.getCardValue(driver) + " matches " +  CARD_VALUE);
			Assert.assertEquals(thankPage.getStoredValue(driver),  STORED_VALUE);
			Log.info("Actual results " + thankPage.getStoredValue(driver) + " matches " +  STORED_VALUE);
			Assert.assertEquals(thankPage.getZonePass(driver),  ZONE_PASS);
			Log.info("Actual results " + thankPage.getZonePass(driver) + " matches " +  ZONE_PASS);
			Assert.assertEquals(thankPage.getDeposit(driver),  CARD_DEPOSIT);
			Log.info("Actual results " + thankPage.getDeposit(driver) + " matches " +  CARD_DEPOSIT);
			Assert.assertEquals(thankPage.getShipping(driver),  SHIPPING);
			Log.info("Actual results " + thankPage.getShipping(driver) + " matches " +  SHIPPING);
			Assert.assertEquals(thankPage.getTotal(driver),  TOTAL);
			Log.info("Actual results " + thankPage.getTotal(driver) + " matches " +  TOTAL);
			Assert.assertEquals(thankPage.getName(driver), FULLNAME);
			Log.info("Actual results " + thankPage.getName(driver) + " matches " + FULLNAME);
			Assert.assertEquals(thankPage.getName(driver), FULLNAME);
			Log.info("Actual results " + thankPage.getName(driver) + " matches " + FULLNAME);
			Assert.assertEquals(thankPage.getAddress(driver), ADDRESS);
			Log.info("Actual results " + thankPage.getAddress(driver) + " matches " + ADDRESS);
			Assert.assertEquals(thankPage.getPostal(driver), POSTAL);
			Log.info("Actual results " + thankPage.getPostal(driver) + " matches " + POSTAL);
			
			
	}
	
	private String parseEmail(ThankYouPage thankPage) throws InterruptedException, AWTException{	
		String actualEmail = thankPage.getEmail(driver).substring(SUBSTRING);
    	return actualEmail;
    }
	
	
	//Sign-in account test case
	@Test(priority=2)
	public void signInTest() throws Exception{
		HomePage homePage = new HomePage(driver);
		homePage.getLandingPage();
		homePage.clickSignIn(driver);
		SignInPage sPage = new SignInPage(driver);
		sPage.enterEmail(driver, expectedEmail);
		sPage.enterPasswd(driver, passwd);
		sPage.clickSignInAccount(driver);
		ManagePage mPage = new ManagePage(driver);
		mPage.clickMyAccount(driver);
		
		Assert.assertEquals(mPage.getName(driver), FULNAME);
		Log.info("Actual results " + mPage.getName(driver) + " matches " + FULNAME);	
		Assert.assertEquals(mPage.getEmail(driver), expectedEmail);
		Log.info("Actual results " + mPage.getEmail(driver) + " matches " + expectedEmail);	
		Utils.waitTime(5000);
		mPage.clickSignOut(driver);
	}
	
	
	//Contact customer service
	@Test(priority=3)
	public void contactTest()throws Exception{
		HomePage homePage = new HomePage(driver);
		homePage.getLandingPage();
		homePage.clickContact(driver);
		
		ContactPage contactPage = new ContactPage(driver);
		contactPage.enterFirstName(driver, FNAME);
	    contactPage.enterLasttName(driver, LNAME);
	    contactPage.enterPhone(driver, PHONE);
	    contactPage.enterEmail(driver, expectedEmail);
	    contactPage.selectFeedback(driver);
	    contactPage.enterMessage(driver, Global.MESSAGE);
	    contactPage.clickSend(driver);
		Assert.assertEquals(homePage.getFeedbackMsg(driver),  SUCCESS);
		Log.info("Actual results " + (homePage.getFeedbackMsg(driver) + " matches " + SUCCESS));		    	
	}
	
	
	@AfterMethod
	public void tearDown() {
		Log.info("TearDown Complete");
		Reporter.log("TearDown Complete");
		driver.quit();

	}

}