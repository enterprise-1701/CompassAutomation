package automationFramework.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public final class Security {

	public static void clickJack(WebDriver driver, String element){
		long startTime = System.currentTimeMillis();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(element))).perform();
			while(System.currentTimeMillis()-startTime<10000){
				action.clickAndHold(driver.findElement(By.xpath(element))).perform();
				}
		action.release(driver.findElement(By.xpath(element))).perform();
	}
}