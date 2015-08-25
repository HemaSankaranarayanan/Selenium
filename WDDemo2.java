package mentor.qa.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDDemo2 {
		
	public static void main(String[] args) throws InterruptedException {
		
		double price1, price2;
		price1 = findprice("DFW");
		System.out.println("Price is " + price1);
		
		price2 = findprice("ORD");
		System.out.println("Price is " + price2);
		
		if (price1 > price2) {
			System.out.println("SFO-ORD is the cheapest");
			}
		else
			System.out.println("SFO-DFW is the cheapest");
		
	}

	public static double findprice(String dest) throws InterruptedException {
		WebDriver wd = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(wd, 10);
		
		wd.get("http://www.orbitz.com");
		
		wd.findElement(By.xpath("//span[@class='primaryRadioMessage needsclick']")).click();
		
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("ar.rt.leaveSlice.orig.key")));
		
		wd.findElement(By.name("ar.rt.leaveSlice.orig.key")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.orig.key")).sendKeys("SFO");
		
		wd.findElement(By.name("ar.rt.leaveSlice.dest.key")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.dest.key")).sendKeys(dest);
		
		wd.findElement(By.name("ar.rt.leaveSlice.date")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.date")).sendKeys("12/17/15");
		
		wd.findElement(By.name("ar.rt.returnSlice.date")).clear();
		wd.findElement(By.name("ar.rt.returnSlice.date")).sendKeys("12/27/15");
		
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.findElement(By.name("search")).click();
				
		Thread.sleep(20000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsTwoColumn")));
			
		String price = wd.findElement(By.xpath("//span[@class='money small-cents small-symbol']")).getText().replace("$", "");
		wd.close();
		
		double lprice = Double.parseDouble(price);			
		return lprice;
	}
	
		
	
}
