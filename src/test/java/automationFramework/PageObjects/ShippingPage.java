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

public class ShippingPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String TITLE = "//*[@id='Content_compOrderCard_editName_ddlTitle']";
	private static final String FIRSTNAME = "//*[@id='Content_compOrderCard_editName_txtFirstName']";
	private static final String LASTNAME = "//*[@id='Content_compOrderCard_editName_txtLastName']";
	private static final String ADDRESS = "//*[@id='Content_compOrderCard_editAddress_txtAddressOne']";
	private static final String CITY = "//*[@id='Content_compOrderCard_editAddress_txtCity']";
	private static final String POSTALCODE = "//*[@id='Content_compOrderCard_editAddress_txtPostalCode']";
	private static final String CONTINUE = "//*[@id='Content_compOrderCard_btnEditAddressContinue']";
	
	public ShippingPage(WebDriver driver) {
		super(driver);
	}

	public void clickContinue(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTINUE)).click();
	}

	public void enterTitle(WebDriver driver, String title) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(TITLE)).sendKeys(title);
	}
	
	public void enterFirstName(WebDriver driver, String name) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(FIRSTNAME)).sendKeys(name);
	}
	
	public void enterLastName(WebDriver driver, String lname) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(LASTNAME)).sendKeys(lname);
	}
	
	public void enterAddress(WebDriver driver, String address) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADDRESS)).sendKeys(address);
	}
	
	public void enterCity(WebDriver driver, String city) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CITY)).sendKeys(city);
	}
	
	public void enterPostalCode(WebDriver driver, String postalCode) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(POSTALCODE)).sendKeys(postalCode);
	}
	
}