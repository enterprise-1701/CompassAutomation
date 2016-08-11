package automationFramework.PageObjects;


import java.awt.AWTException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.WebClient;

import automationFramework.Utilities.Global;
import automationFramework.Utilities.Utils;

public class BaseClass {
	
	// Element Locators
	    private static final String HOME_LINK = "//*[@id='lnkHomeDT']";
		private static final String HELP_LINK = "//*[@id='lnkHelpDT']";	
		private static final String REGISTER = "//*[@id='aNeedAccount']";
		private static final String SIGN_IN = "//*[@id='aSignIn']";
		private static final String CONTACT = "//*[@id='footer']/div/div[2]/ul/ul/li[1]/a";
		
	public static WebDriver driver;

	public BaseClass(WebDriver driver) {
		BaseClass.driver = driver;
	}
	
	public void getLandingPage() throws Exception {
		  
		try{
			driver.get(Global.URL1);
			int code = getStatusCode(200);
			System.out.println("Status code is:" + code);

		} catch (Exception e) {
			Reporter.log("landing page not Found");
			throw (e);
		}
	}
	
	public void clickHome(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(HOME_LINK)).click();
	}
	
	public void clickHelp(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(HELP_LINK)).click();
	}
	
	public void clickRegister(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(REGISTER)).click();
	}
	
	public void clickSignIn(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(SIGN_IN)).click();
	}
	
	public void clickContact(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(CONTACT)).click();
	}
	
	
	public String getCookie(String cookie) {
		driver.get(Global.URL1);
		Set<Cookie> cookies = driver.manage().getCookies();
		Cookie UID = driver.manage().getCookieNamed(cookie);
		return UID.getValue();
	}
	
	public static int getStatusCode(long appUserId) throws IOException {
		WebClient webClient = new WebClient();
		int code = webClient.getPage(Global.URL1).getWebResponse().getStatusCode();
		return code;
	}
	
	public static String getPageContent() throws IOException {
		WebClient webClient = new WebClient();
		String content = webClient.getPage(Global.URL1).getWebResponse().getContentAsString();
		return content;
	}
	
	public boolean accessSignedInUrl(WebDriver driver, String url) throws InterruptedException, AWTException{
		driver.navigate().to(url);
		String currentUrl = driver.getCurrentUrl();
		System.out.println("landing page url " + currentUrl);
		if(currentUrl.equalsIgnoreCase(url)){
			return true;
		}
		return false;		
	}	     	   
}
