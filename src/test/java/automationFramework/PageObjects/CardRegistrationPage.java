package automationFramework.PageObjects;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.WebClient;

import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;

public class CardRegistrationPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String EMAIL = "//*[@id='Content_compOrderCard_emailInfo_txtEmail']";
	private static final String PASSWD = "//*[@id='Content_compOrderCard_passInfo_txtPassword']";
	private static final String PASSWD2 = "//*[@id='Content_compOrderCard_passInfo_txtPasswordConfirmation']";
	private static final String SECURITY_ANSWER = "//*[@id='Content_compOrderCard_questionInfo_txtSecurityAnswer']";
	private static final String EMAIL_PREFERNCES = "//*[@id='Content_pnlCardDetails']/div[1]/div[2]/span/div/label";
	private static final String CONTINUE = "//*[@id='Content_compOrderCard_btnPrePurchaseRegisterContinue']";
	private static final String PASSWD_ERROR = "//*[@id='Content_compOrderCard_passInfo_ctl02']";
	private static final String PASSWD_ERROR2 = "//*[@id='Content_compOrderCard_passInfo_ctl03']";
	private static final String PASSWD_ERROR3 = "//*[@id='Content_compOrderCard_passInfo_cv_txtPasswordConfirmation']";
	
	public CardRegistrationPage(WebDriver driver) {
		super(driver);
	}

	public String getPasswdError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PASSWD_ERROR)).getText();
	}
	
	public String getPasswdError2(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PASSWD_ERROR2)).getText();
	}
	
	public String getPasswdError3(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PASSWD_ERROR3)).getText();
	}
	
	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EMAIL)).sendKeys(email);
	}
	
	public void clearPasswd(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWD)).clear();
	}
	
	public void enterPasswd(WebDriver driver, String passwd) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWD)).sendKeys(passwd);
	}
	
	public boolean isPasswdMasked(WebDriver driver) throws InterruptedException, AWTException {
		boolean isMasked =  driver.findElement(By.xpath(PASSWD)).getAttribute("type").equals("password");
		return isMasked;
	}
	
	public void enterPasswd2(WebDriver driver, String passwd2) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWD2)).sendKeys(passwd2);
	}
	
	public boolean isPasswd2Masked(WebDriver driver) throws InterruptedException, AWTException {
		boolean isMasked =  driver.findElement(By.xpath(PASSWD2)).getAttribute("type").equals("password");
		return isMasked;
	}
	
	public void clickPasswd2(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWD2)).click();
	}
	
	public void enterSecurityAnswer(WebDriver driver, String answer) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(SECURITY_ANSWER)).sendKeys(answer);
	}
	
	public void clickEmailPref(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EMAIL_PREFERNCES)).click();
	}

	public void clickContinue(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTINUE)).click();
	}
}