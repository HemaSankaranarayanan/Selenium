package mentor.qa.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDown {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(driver, 80);
		driver.get("http://www.travelocity.com");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[@class='icon icon-flights']")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-origin")));
		
		Select sel1 = new Select(driver.findElement(By.id("flight-adults")));
		Select sel2 = new Select(driver.findElement(By.id("flight-children")));
		
		List<WebElement> options = new ArrayList<WebElement>(sel1.getOptions());
		
		for (int i=0; i<options.size(); i++) {
			String optionName = sel1.getOptions().get(i).getText();
			System.out.println(optionName);
			
		}
				
		sel1.selectByValue("2");
		sel2.selectByIndex(2);		
		

	}

}
