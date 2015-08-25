package mentor.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Kayak {

	public static void main(String[] args) throws InterruptedException {
		WebDriver wd = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(wd, 60);
		
		wd.get("http://www.kayak.com");
		
		wd.findElement(By.id("flights-link")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("origin")));
				
		wd.findElement(By.id("origin")).clear();
		wd.findElement(By.id("origin")).sendKeys("SFO");
		
		wd.findElement(By.id("destination")).clear();
		wd.findElement(By.id("destination")).sendKeys("DFW");
		
		wd.findElement(By.id("depart_date")).clear();
		wd.findElement(By.id("depart_date")).sendKeys("12/17/2015");
		
		//wd.findElement(By.id("travel_dates-end")).clear();
		wd.findElement(By.id("travel_dates-end-display")).sendKeys("12/27/2015");
		
		//wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.findElement(By.id("fdimgbutton")).click();
				
		//Thread.sleep(20000);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='results_price']")));
					
		String price = wd.findElement(By.xpath("//span[@class='results_price']")).getText().replace("$", "").replace(",", "");
						
		System.out.println("Lowest Price is " + price);
		
		wd.close();
		

	}

}
