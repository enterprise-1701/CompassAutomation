package automationFramework.Utilities;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public final class Security {

	public static void clickJack(WebDriver driver, String element, int time){
		long startTime = System.currentTimeMillis();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath(element))).perform();
			while(System.currentTimeMillis()-startTime<time){
				action.clickAndHold(driver.findElement(By.xpath(element))).perform();
				}
		action.release(driver.findElement(By.xpath(element))).perform();
	}
	
	public static boolean checkPageContent(WebDriver driver, String url, List<String>data) throws Exception{
		
		driver.navigate().to(url);
		String errorPageContent = driver.getPageSource();	

		for (int i =0; i < data.size(); i++){
			System.out.println("value of array list is: " + data.get(i));
			boolean dataDisplayed = errorPageContent.contains(data.get(i));
			if(dataDisplayed){
				System.out.println("The words are on the page   " + dataDisplayed);
				return true;
				
			}
			else{
				System.out.println("The words are not on the page " + dataDisplayed);
			}
		}
		
		return false;
	}
	
}