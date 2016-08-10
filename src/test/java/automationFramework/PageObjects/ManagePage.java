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

public class ManagePage extends BaseClass {


	// Element Locators
	private static final String NAME = "//*[@id='page']/div/section[1]/div[1]/div[1]/span[2]";	
	private static final String EMAIL = "//*[@id='page']/div/section[2]/div[1]/div/span[2]";
	private static final String MYACCOUNT = "//*[@id='lnkAccount']";
	private static final String SIGNOUT = "//*[@id='btnSignOut']";
	
	public ManagePage(WebDriver driver) {
		super(driver);
	}
	
	public String getName(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(NAME)).getText();
	}
	
	public String getEmail(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(EMAIL)).getText();
	}
	
	public void clickMyAccount(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(MYACCOUNT)).click();
	}
	
	public void clickSignOut(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SIGNOUT)).click();
	}

}