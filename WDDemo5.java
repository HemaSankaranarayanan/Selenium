package mentor.qa.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDDemo5 {
		
	public static void main(String[] args) throws Exception {
		
		//String[][] data;
		//String datafile = "/Users/HEMA/selenium/Data.xlsx";
		String[][] steps;
		String stepsfile = "/Users/HEMA/selenium/Keyword.xlsx";
		steps = excelRead(stepsfile);
		
		HashMap<Double, String> map = new HashMap<Double, String>();
		String itin;
						
		WebDriver wd = null;
				
		for (int i=1; i < steps.length; i++) {
			if (steps[i][0].equalsIgnoreCase("Y")) {
				
				
				if (steps[i][4].equalsIgnoreCase("open_browser")) {
					wd = open_browser(steps[i][7]);
				}
				
				if (steps[i][4].equalsIgnoreCase("navigate_to")) {
					navigate_to(wd,steps[i][7]);					
				}
					
				if (steps[i][4].equalsIgnoreCase("click_element")) {
					click_element(wd,steps[i][5],steps[i][6]);
				}	
				
				if (steps[i][4].equalsIgnoreCase("verify_element")) {
					verify_element(wd,steps[i][5],steps[i][6]);
				}
				
				if (steps[i][4].equalsIgnoreCase("send_keys")) {
					send_keys(wd,steps[i][5],steps[i][6],steps[i][7]);
				}
				
				if (steps[i][4].equalsIgnoreCase("use_sleep")) {
					Thread.sleep(Long.parseLong(steps[i][7]));
				}
				
				if (steps[i][4].equalsIgnoreCase("store_text")) {
					String lprice = store_text(wd,steps[i][5],steps[i][6],steps[i][7]);
					double price = Double.parseDouble(lprice);
					itin = steps[i][1];
					System.out.println(itin + "     " + price);
					map.put(price, itin);	

				}
				
				if (steps[i][4].equalsIgnoreCase("close_browser")) {
					wd.close();

				}
							
			}			
		}
		
		List<Double> list = new ArrayList<Double>(map.keySet());
		Collections.sort(list);
		
		System.out.println("The mimimum fare is " + list.get(0) + " and the itinerary is " + map.get(list.get(0)));
				
	}

	

	private static WebDriver open_browser(String browser) {
		
		WebDriver wd1 = null;
		
		if (browser.equalsIgnoreCase("Firefox")) {
			wd1 = new FirefoxDriver();
		}
		if (browser.equalsIgnoreCase("IE")) {
			wd1 = new InternetExplorerDriver();
		}
		if (browser.equalsIgnoreCase("Chrome")) {
			wd1 = new ChromeDriver();
		}
						
		return wd1;
	}
	
	private static void navigate_to(WebDriver wd, String url) {
		wd.get(url);		
	}
	
	
	private static void click_element(WebDriver wd, String locator, String locStr) {
				
		if (locator.equalsIgnoreCase("xpath")) {
			wd.findElement(By.xpath(locStr)).click();
		}
		
		if (locator.equalsIgnoreCase("name")) {
			wd.findElement(By.name(locStr)).click();
		}
		
		if (locator.equalsIgnoreCase("id")) {
			wd.findElement(By.id(locStr)).click();
		}		
		
	}

	
	private static void verify_element(WebDriver wd, String locator, String locStr) {
		WebDriverWait wait1 = new WebDriverWait(wd, 60);
		
		
		if (locator.equalsIgnoreCase("xpath")) {
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locStr)));
		}
		
		if (locator.equalsIgnoreCase("name")) {
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name(locStr)));
		}
		
		if (locator.equalsIgnoreCase("id")) {
			wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id(locStr)));
		}	
		
	}

	private static void send_keys(WebDriver wd, String locator, String locStr, String value) {
				
		if (locator.equalsIgnoreCase("xpath")) {
			wd.findElement(By.xpath(locStr)).clear();
			wd.findElement(By.xpath(locStr)).sendKeys(value);
		}
		
		if (locator.equalsIgnoreCase("name")) {
			wd.findElement(By.name(locStr)).clear();
			wd.findElement(By.name(locStr)).sendKeys(value);
		}
		
		if (locator.equalsIgnoreCase("id")) {
			wd.findElement(By.id(locStr)).clear();
			wd.findElement(By.id(locStr)).sendKeys(value);
		}	
				
	}
	
	private static String store_text(WebDriver wd, String locator,
			String locStr, String optLocStr) {
		
		String tprice = null;
		
		if (optLocStr.equalsIgnoreCase("-")) {
			if (locator.equalsIgnoreCase("xpath")) {
				tprice = wd.findElement(By.xpath(locStr)).getText().replace("$", "").replace("," , "");
			}
			
			if (locator.equalsIgnoreCase("name")) {
				tprice = wd.findElement(By.name(locStr)).getText().replace("$", "").replace("," , "");
			}
			
			if (locator.equalsIgnoreCase("id")) {
				tprice = wd.findElement(By.id(locStr)).getText().replace("$", "").replace("," , "");
			}	
		}
				
		else {
			
			if (locator.equalsIgnoreCase("xpath")) {
				tprice = wd.findElement(By.xpath(locStr)).getText().replace("$", "").replace("," , "") + wd.findElement(By.xpath(optLocStr)).getText().replace("$", "").replace("," , "");
			}
			
			if (locator.equalsIgnoreCase("name")) {
				tprice = wd.findElement(By.name(locStr)).getText().replace("$", "").replace("," , "") + wd.findElement(By.name(optLocStr)).getText().replace("$", "").replace("," , ""); 
			}
			
			if (locator.equalsIgnoreCase("id")) {
				tprice = wd.findElement(By.id(locStr)).getText().replace("$", "").replace("," , "") + wd.findElement(By.id(optLocStr)).getText().replace("$", "").replace("," , "");
			}				
			
		}	
		
				
		return tprice;
	}
	

	
	
	public static String[][] excelRead(String filename) throws Exception {
		// TODO Auto-generated method stub
		File excel = new File(filename);
		FileInputStream fis = new FileInputStream(excel);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet("Sheet1");
		
		//getlastRowNum returns the # of rows starting with 0 as first row. SO to get actual row # in excel, add1
		int rowNum = ws.getLastRowNum() + 1;
		int colNum = ws.getRow(0).getLastCellNum();
		String [][] inputData = new String[rowNum][colNum]; 
		
		for (int i=0; i < rowNum; i++) {
			XSSFRow row = ws.getRow(i);
			for (int j=0; j < colNum; j++) {
				XSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				inputData[i][j] = value;
				//System.out.println("The value is " + value);				
			}			
		}
		wb.close();
		return inputData;

	}

	public static String cellToString(XSSFCell cell) throws Exception {
		// TODO Auto-generated method stub
		int type;
		Object result;
		type = cell.getCellType();
		
		switch (type) {
		
		case 0 : //numeric or date value
			
			if (DateUtil.isCellDateFormatted(cell)) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				result = sdf.format(cell.getDateCellValue());				
				 
            } else {
            	result = cell.getNumericCellValue();
            }
			
			break;
			
		case 1 : // string value
			result = cell.getStringCellValue();
			break;
			
		case 3 : // Blank cell
			result = "-";
			break;
			
		default :
			throw new Exception("There is no support for this cell type");
		
		}
		
		return result.toString();
	}
	
	
		
	
}
