package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ManagePaymentPage extends BaseClass {

	// Element Locators
	private static final String PAYMENT_TYPE = "//*[@id='page']/div/section/div[2]/div[1]/span[1]";	
	private static final String BACK = "//*[@id='Content_btnCancel']";

	
	public ManagePaymentPage(WebDriver driver) {
		super(driver);
	}
	
	public String getPaymentType(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(PAYMENT_TYPE)).getText();
	}
	
	public void clickBack(WebDriver driver) throws InterruptedException, AWTException {
		driver.findElement(By.xpath(BACK)).click();
	}


}