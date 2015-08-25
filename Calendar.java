package mentor.qa.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Calendar {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(driver, 80);
		driver.get("http://www.travelocity.com");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[@class='icon icon-flights']")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-origin")));
		
		driver.findElement(By.id("flight-departing")).click();
		WebElement cal = driver.findElement(By.xpath("//div[@class='cal']//section[1]//ul[@class='cal-dates']"));
		
		List<WebElement> dates = new ArrayList<WebElement>(cal.findElements(By.tagName("li")));
		for (WebElement cell : dates) {
			if (cell.getText().equals("10")) {
				cell.findElement(By.linkText("10")).click();
				break;
			}
						
		}
		
			
		

	}

}
