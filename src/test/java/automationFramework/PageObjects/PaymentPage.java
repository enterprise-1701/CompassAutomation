package automationFramework.PageObjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.WebClient;

import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;

public class PaymentPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String NAME = "//*[@id='Content_compOrderCard_editPayment_compPaymentMethod_txtCardName']";
	private static final String CC_NUMBER = "//*[@id='Content_compOrderCard_editPayment_compPaymentMethod_txtCardNumber']";
	private static final String EXPIRE_DATE = "//*[@id='Content_compOrderCard_editPayment_compPaymentMethod_ddlCCMonth']";
	private static final String EXPIRE_YEAR = "//*[@id='Content_compOrderCard_editPayment_compPaymentMethod_ddlCCYear']";
	private static final String CVV = "//*[@id='Content_compOrderCard_editPayment_compPaymentMethod_txtCardCVV']";
	private static final String MAILING_INFO = "//*[@id='Content_compOrderCard_editPayment_compPaymentMethod_pnlUseMailingAddress']/span/div/label";
	private static final String PHONE = "//*[@id='Content_compOrderCard_editPayment_compPaymentMethod_pdInfo_txtPhone']";
	private static final String CONTINUE = "//*[@id='Content_compOrderCard_btnEditPaymentContinue']";
	
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
	public PaymentPage(WebDriver driver) {
		super(driver);
	}

	public void enterName(WebDriver driver, String name) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(NAME)).sendKeys(name);
	}
	
	public void enterCC(WebDriver driver, String ccnumber) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CC_NUMBER)).sendKeys(ccnumber);
	}
	
	public void enterExpDate(WebDriver driver, String expdate) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EXPIRE_DATE)).sendKeys(expdate);
	}
	
	public void enterExpYear(WebDriver driver, String expyear) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EXPIRE_YEAR)).sendKeys(expyear);
	}
	
	public void enterCvv(WebDriver driver, String cvv) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CVV)).sendKeys(cvv);
	}
	
	public void clickMailingInfo(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(MAILING_INFO)).click();
	}

	public void enterPhone(WebDriver driver, String phone) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(CONTINUE)));
		driver.findElement(By.xpath(PHONE)).sendKeys(phone);
	}
	
	public void clickContinue(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTINUE)).click();
	}
}