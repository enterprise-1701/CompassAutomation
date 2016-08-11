package automationFramework.PageObjects;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CardInfoPage extends BaseClass {

	// Element Locators
	private static final String LOGO = "//*[@id='lnkCompass']";
	private static final String CARD_INFO_PANEL = "//*[@id='main-panel-desc']/h1";
	
	public CardInfoPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickLogo(WebDriver driver) throws InterruptedException, AWTException{
		driver.findElement(By.xpath(LOGO)).click();
	}
	
	public String getCardInfoPanel(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CARD_INFO_PANEL)).getText();
	}
	
	public boolean isCardInfoPanelDisplayed(WebDriver driver) throws InterruptedException, AWTException {
		return driver.findElement(By.xpath(CARD_INFO_PANEL)).isDisplayed();
	}

}