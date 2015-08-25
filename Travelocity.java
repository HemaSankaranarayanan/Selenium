package mentor.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Travelocity {

	public static void main(String[] args) throws InterruptedException {
		WebDriver wd = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(wd, 60);
		
		wd.get("http://www.travelocity.com");
		
		wd.findElement(By.xpath("//span[@class='icon icon-flights']")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-origin")));
				
		wd.findElement(By.id("flight-origin")).clear();
		wd.findElement(By.id("flight-origin")).sendKeys("SFO");
		
		wd.findElement(By.id("flight-destination")).clear();
		wd.findElement(By.id("flight-destination")).sendKeys("DFW");
		
		wd.findElement(By.id("flight-departing")).clear();
		wd.findElement(By.id("flight-departing")).sendKeys("12/17/2015");
		
		wd.findElement(By.id("flight-returning")).clear();
		wd.findElement(By.id("flight-returning")).sendKeys("12/27/2015");
		
		//wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.findElement(By.id("search-button")).click();
				
		//Thread.sleep(20000);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("xsell-banner-default")));
					
		String price = wd.findElement(By.xpath("//span[@class='dollars price-emphasis bestValue-emphasis bestValueDetails-emphasis']")).getText().replace("$", "").replace(",", "")
				+ wd.findElement(By.xpath("//span[@class='cents secondary price-emphasis bestValue-emphasis bestValueDetails-emphasis']")).getText();
		
		System.out.println("Lowest Price is " + price);
		
		wd.close();
		

	}

}
