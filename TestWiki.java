package mentor.qa.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestWiki {

	public static void main(String[] args) {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.wikipedia.org");
		WebElement langDropDown = driver.findElement(By.id("searchLanguage"));
		Select dropDown = new Select(langDropDown);
		List<WebElement> options = new ArrayList<WebElement>(dropDown.getOptions());
		for (WebElement option : options) {
			System.out.println(option.getText());
		}
		System.out.println(options.size());
		driver.quit();
	}

}
