package automationFramework.PageObjects;

import java.awt.AWTException;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SignInPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String EMAIL = "//*[@id='Content_emailInfo_txtEmail']";
	private static final String PASSWD = "//*[@id='Content_passwordInfo_txtPassword']";
	private static final String SIGNIN = "//*[@id='Content_btnSignIn']";
	private static final String LOGIN_ERROR = "//*[@id='pnlNotificationTray']/div";
	
	public SignInPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterEmail(WebDriver driver, String email) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EMAIL)).sendKeys(email);
	}
	
	public void clearEmail(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(EMAIL)).clear();
	}
	
	public void enterPasswd(WebDriver driver, String passwd) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWD)).sendKeys(passwd);
	}
	
	public void clearPasswd(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(PASSWD)).clear();
	}
	
	public void clickSignInAccount(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SIGNIN)).click();
	}
	
	public String getLoginError(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(LOGIN_ERROR)).getText();
	}
}