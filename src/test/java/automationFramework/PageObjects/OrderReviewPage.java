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

public class OrderReviewPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String CONTINUE = "//*[@id='Content_compOrderCard_btnContinue']";
	private static final String CANCEL = "//*[@id='Content_pnlCardDetails']/div[2]/a";
	private static final String ADD_MORE = "//*[@id='Content_compOrderCard_btnAddMoreCards']";
	
	public OrderReviewPage(WebDriver driver) {
		super(driver);
	}

	public void clickContinue(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CONTINUE)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}
	
	public void clickAddMore(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADD_MORE)).click();
	}
	
}