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

public class SignInPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String EMAIL = "//*[@id='Content_emailInfo_txtEmail']";

	private static final String PASSWD = "//*[@id='Content_passwordInfo_txtPassword']";
	
	private static final String SIGNIN = "//*[@id='Content_btnSignIn']";
	
	public SignInPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EMAIL)).sendKeys(email);
	}
	
	public void enterPasswd(WebDriver driver, String passwd) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWD)).sendKeys(passwd);
	}
	
	public void clickSignInAccount(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SIGNIN)).click();
	}
	
}