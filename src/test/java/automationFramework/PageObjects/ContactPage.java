package automationFramework.PageObjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.WebClient;

import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;

public class ContactPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String FIRST_NAME = "//*[@id='Content_ctl05_txtFirstName']";	
	private static final String LAST_NAME = "//*[@id='Content_ctl05_txtLastName']";	
	private static final String PHONE = "//*[@id='Content_ctl05_txtPhone']";	
	private static final String EMAIL = "//*[@id='Content_ctl05_emailInfo_txtEmail']";	
	private static final String FEEDBACK_TYPE = "//*[@id='Content_ctl05_ddlFeedbackType']";	
	private static final String MESSAGE = "//*[@id='Content_ctl05_txtMessage']";
	private static final String SEND_FEEDBACK = "//*[@id='Content_ctl05_btnSend']";
	
	
	public ContactPage(WebDriver driver) {
		super(driver);
	}

	public void enterFirstName(WebDriver driver, String fname) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(FIRST_NAME)).sendKeys(fname);
	}
	
	public void enterLasttName(WebDriver driver, String lname) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LAST_NAME)).sendKeys(lname);
	}
	
	public void enterPhone(WebDriver driver, String phone) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PHONE)).sendKeys(phone);
	}
	
	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EMAIL)).sendKeys(email);
	}
	
	public void selectFeedback(WebDriver driver) throws InterruptedException, AWTException {
		Select dropList = new Select(driver.findElement(By.xpath(FEEDBACK_TYPE)));
		dropList.selectByVisibleText("Other");
		
	}
	
	public void enterMessage(WebDriver driver, String message) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(MESSAGE)).sendKeys(message);
	}
	
	public void clickSend(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(SEND_FEEDBACK)).click();
	}
}