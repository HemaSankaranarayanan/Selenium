package mentor.qa.selenium;

import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SwitchWindow {

	public static void main(String[] args) {
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.enterprise.com");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//table[@id='standardTab']//a[@class='popup' and contains(@href,'pickAirport')]")).click();
	//	driver.findElement(By.xpath("//table[@id='standardTab']//a[@class='popup' and contains(@href,'pickPort')]")).click();
		
		String parentHandle = driver.getWindowHandle();
		
		Set<String> handles = new TreeSet<String> (driver.getWindowHandles());
		
		for (String handle : handles) {
			
			if (!handle.equals(parentHandle)) {
			 driver.switchTo().window(handle);
			 if (driver.getTitle().equalsIgnoreCase("Enterprise Rent A Car - Choose an airport location")) {
				 driver.findElement(By.xpath("//div[@id='content']//a[text()='San Francisco, CA']")).click();				 				 
			 }				
				
			 driver.switchTo().window(parentHandle);
			}
			
								
		}
		
		
		
		
		
		

	}

}
