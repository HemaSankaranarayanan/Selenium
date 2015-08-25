package mentor.qa.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestYelp {
	public static WebDriver driver = new FirefoxDriver();
	driver.manage().window().maximize();
	
	public static void main(String[] args) throws InterruptedException {
		
		
		driver.get("http://www.yelp.com");	
		WebElement navigation = driver.findElement(By.xpath("//div[@class='navigation']"));
				
		List<WebElement> links = new ArrayList<WebElement>(navigation.findElements(By.tagName("li")));
		
		for (WebElement link : links) {
			
			String tempText = link.findElement(By.tagName("span")).getText();
			System.out.println(tempText.trim());
			driver.findElement(By.linkText(tempText)).click();
			Thread.sleep(15000);
			getList();
			System.out.println("-----------------------------------");
					
		}	

	}
	
	
	public static void getList() {
			//WebElement reslist = driver.findElement(By.xpath("//ul[@class='ylist ylist-bordered']"));
				WebElement reslist = driver.findElement(By.xpath("//ul[@class='content-list ieSucks']"));
				List<WebElement> resnames = new ArrayList<WebElement>(reslist.findElements(By.tagName("li")));
				
				for (int i=0 ; i<resnames.size() ; i++) {
					String restaurantName = resnames.get(i).findElement(By.tagName("span")).getText();
					if (!(restaurantName.trim().isEmpty())) {
						System.out.println(restaurantName.trim());
					}					
				}
				
				/*for (WebElement res : resnames) {
					
					String restaurantName = res.findElement(By.tagName("span")).getText();
					System.out.println(restaurantName.trim());
					//System.out.println(res.getText());
					
				}*/
		
		
	}
	
	

}
