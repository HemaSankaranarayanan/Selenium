package mentor.qa.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RadioButtonAndCheckBox {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(driver, 80);
		driver.get("http://www.orbitz.com");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[@class='primaryRadioMessage needsclick']")).click();		
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("ar.rt.leaveSlice.orig.key")));
					
		List<WebElement> radioButtons = new ArrayList<WebElement>(driver.findElements(By.name("search.ar.type.code")));
		boolean checked = false;
		int num = 0;
				
		for (int i=0; i<radioButtons.size(); i++) {
			
			checked = radioButtons.get(i).isSelected(); 
					
			if (checked) {
				num = i;
				
			}				
		}
		
		radioButtons.get(num+1).click();
		
		Thread.sleep(2000);
		driver.findElement(By.name("ar.ow.narrowSel")).click();	

	}

}
