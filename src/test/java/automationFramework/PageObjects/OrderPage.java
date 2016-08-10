package automationFramework.PageObjects;

import java.awt.AWTException;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class OrderPage extends BaseClass {

	public static Pattern p1;

	// Element Locators
	private static final String ADD_VALUE = "//*[@id='Content_pnlCardDetails']/div[3]/div[1]/div[1]/span/div/label";
	private static final String ADD_PASS = "//*[@id='Content_pnlCardDetails']/div[3]/div[2]/div/span/div/label";
	private static final String TEN = "//*[@id='Content_compOrderCard_compAddEditCard_svList_rptSvOpts_lblTitle_0']";
	private static final String ZONE1 = "//*[@id='Content_compOrderCard_compAddEditCard_tlMonthlyPasses_rptPasses_pnlWrap_0']/div/label/span[2]/span[2]";
	private static final String CANCEL = "//*[@id='Content_compOrderCard_lnkBack']";
	private static final String ADD_TO_ORDER = "//*[@id='Content_compOrderCard_btnAddToOrder']";
	
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
	public OrderPage(WebDriver driver) {
		super(driver);
	}

	public void clickAddValue(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADD_VALUE)).click();
	}
	
	public void clickTen(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TEN)));
		driver.findElement(By.xpath(TEN)).click();
	}
	
	public void clickAddPass(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADD_PASS)).click();
	}
	
	public void clickZone1(WebDriver driver) throws InterruptedException, AWTException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ZONE1)));
		driver.findElement(By.xpath(ZONE1)).click();
	}
	
	public void clickAddToOrder(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(ADD_TO_ORDER)).click();
	}

	public void clickCancel(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(CANCEL)).click();
	}
	
}