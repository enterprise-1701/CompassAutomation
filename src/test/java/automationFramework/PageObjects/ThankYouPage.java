package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


public class ThankYouPage extends BaseClass {

	// Element Locators
	private static final String CARD_VALUE = "//*[@id='Content_pnlCardDetails']/div[3]/div[1]/div[1]/div[1]/p";
	private static final String STORED_VALUE = "//*[@id='Content_pnlCardDetails']/div[3]/div[1]/div[1]/div[2]/p";
	private static final String ZONE_PASS = "//*[@id='Content_pnlCardDetails']/div[3]/div[1]/div[1]/div[3]/p";
	private static final String CARD_DEPOSIT = "//*[@id='Content_pnlCardDetails']/div[3]/div[1]/div[1]/div[4]/p";
	private static final String SHIPPING = "//*[@id='Content_pnlCardDetails']/div[3]/div[1]/div[2]/p";
	private static final String TOTAL = "//*[@id='Content_pnlCardDetails']/div[3]/div[1]/div[3]/p";
	private static final String NAME = "//*[@id='Content_pnlCardDetails']/div[3]/div[2]/div[2]/div[1]/div[2]";
	private static final String ADDRESS = "//*[@id='Content_pnlCardDetails']/div[3]/div[2]/div[2]/div[2]/div[2]/div[1]";
	private static final String ADDRESS2 = "//*[@id='Content_pnlCardDetails']/div[3]/div[2]/div[2]/div[2]/div[2]/div[3]";
	private static final String POSTAL = "//*[@id='Content_pnlCardDetails']/div[3]/div[2]/div[2]/div[2]/div[2]/div[4]";
	private static final String PAYMENT = "//*[@id='Content_pnlCardDetails']/div[3]/div[2]/div[4]/div/div[1]/div[2]/div[1]";
	private static final String RECEIPT =  "//*[@id='horse']";
	private static final String ORDER_NUMBER = "//*[@id='Content_pnlCardDetails']/div[2]/span[1]";
	private static final String ORDER_DATE = "//*[@id='Content_pnlCardDetails']/div[2]/span[2]";
	private static final String AUTHORIZATION = "//*[@id='Content_compOrderCard_compOrderReceipt_pAuthCode']";
	
	public ThankYouPage(WebDriver driver) {
		super(driver);
	}
	
	public Boolean isOrderNumberDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ORDER_NUMBER)).isDisplayed();
	}
	
	public Boolean isOrderDateDisplayed (WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ORDER_DATE)).isDisplayed();
	}
	
	public Boolean isAuthorizationDisplayed (WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(AUTHORIZATION)).isDisplayed();
	}
	
	public Boolean isReceiptDisplayed (WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(RECEIPT)).isDisplayed();
	}
	
	public String getEmail(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(RECEIPT)).getText();
	}
	
	public String getCardValue(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CARD_VALUE)).getText();
	}

	public String getStoredValue(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(STORED_VALUE)).getText();
	}

	public String getZonePass(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ZONE_PASS)).getText();
	}
	
	public String getDeposit(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CARD_DEPOSIT)).getText();
	}
	
	public String getShipping(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(SHIPPING)).getText();
	}
	
	public String getTotal(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(TOTAL)).getText();
	}
	
	public String getName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NAME)).getText();
	}
	
	public String getAddress(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ADDRESS)).getText();
	}
	
	public String getAddress2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(ADDRESS2)).getText();
	}
	
	public String getPayment(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PAYMENT)).getText();
	}
	
	public String getPostal(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(POSTAL)).getText();
	}
	
}