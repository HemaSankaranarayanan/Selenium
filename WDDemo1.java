package mentor.qa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDDemo1 {
	public static final WebDriver wd = new FirefoxDriver();

	
	public static void main(String[] args) throws InterruptedException {
			
		 orbitzCheck();
		
	}

	public static void orbitzCheck() throws InterruptedException {
		
		WebDriverWait wait1 = new WebDriverWait(wd, 80);
		
		wd.get("http://www.orbitz.com");
		
		wd.findElement(By.xpath("//span[@class='primaryRadioMessage needsclick']")).click();
		
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("ar.rt.leaveSlice.orig.key")));
		
		wd.findElement(By.name("ar.rt.leaveSlice.orig.key")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.orig.key")).sendKeys("DFW");
		
		wd.findElement(By.name("ar.rt.leaveSlice.dest.key")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.dest.key")).sendKeys("SFO");
		
		wd.findElement(By.name("ar.rt.leaveSlice.date")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.date")).sendKeys("12/17/15");
		
		wd.findElement(By.name("ar.rt.returnSlice.date")).clear();
		wd.findElement(By.name("ar.rt.returnSlice.date")).sendKeys("12/27/15");
		
		//wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.findElement(By.name("search")).click();
				
		Thread.sleep(20000);
		//wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsTwoColumn")));
			
		String price = wd.findElement(By.xpath("//span[@class='money small-cents small-symbol']")).getText();
		System.out.println("Lowest Price is " + price);
	}
	
	
	
	
	
}
