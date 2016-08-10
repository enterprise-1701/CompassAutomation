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

public class ReviewPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String AUTHORIZE = "//*[@id='Content_compOrderCard_btnAuthorize']";
	private static final String CANCEL = "//*[@id='Content_compOrderCard_lnkCancelOrder']";
	
	public ReviewPage(WebDriver driver) {
		super(driver);
	}

	public void clickAuthorize(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(AUTHORIZE)).click();
	}
	
	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}
	
}