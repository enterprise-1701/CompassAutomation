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

public class HomePage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String ORDER_CARD = "//*[@id='Content_lbOrderCard']";	
	private static final String FEEDBACK_SUCCESS = "//*[@id='pnlNotificationTray']/div";
	
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public void clickOrderCard(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ORDER_CARD)).click();
		Utils.waitTime(3000);
	}
	
	public String getFeedbackMsg(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(FEEDBACK_SUCCESS)).getText();
	}
	
}