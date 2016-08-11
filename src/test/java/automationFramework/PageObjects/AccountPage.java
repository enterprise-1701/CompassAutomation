package automationFramework.PageObjects;

import java.awt.AWTException;
import automationFramework.Utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class AccountPage extends BaseClass {


	// Element Locators
	private static final String NAME = "//*[@id='page']/div/section[1]/div[1]/div[1]/span[2]";	
	private static final String EMAIL = "//*[@id='page']/div/section[2]/div[1]/div/span[2]";
	private static final String PAYMENT_TYPE = "//*[@id='Content_pnlPrimaryPaymentMethod']/div[3]/span[2]";
	private static final String PAYMENT_TYPE_AUTOLOAD = "//*[@id='Content_pnlAutoloadPaymentMethod']/div[3]/span[2]";
	private static final String MYACCOUNT = "//*[@id='lnkAccount']";
	private static final String SIGNOUT = "//*[@id='btnSignOut']";
	private static final String MANAGE_PMT  = "//*[@id='Content_pnlPrimaryPaymentMethod']/div[1]/a";
	private static final String MANAGE_PMT_AUTOLOAD = "//*[@id='Content_pnlAutoloadPaymentMethod']/div[1]/a";
	private static final String LOGO = "//*[@id='lnkCompass']";
	
	
	public AccountPage(WebDriver driver) {
		super(driver);
	}
	
	public String getName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NAME)).getText();
	}
	
	public String getEmail(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(EMAIL)).getText();
	}
	
	public String getPaymentType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PAYMENT_TYPE)).getText();
	}
	
	public void clickManagePaymentType(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(MANAGE_PMT)).click();
	}
	
	public void clickManageAutoloadPaymentType(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(MANAGE_PMT_AUTOLOAD)).click();
	}
	
	public String getAutoloadPaymentType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PAYMENT_TYPE_AUTOLOAD)).getText();
	}
	
	public void clickMyAccount(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(MYACCOUNT)).click();
	}
	
	public void clickSignOut(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SIGNOUT)).click();
	}
	
	public void clickJackLogo(WebDriver driver, int time){
		Security.clickJack(driver, LOGO, time);
	}

}