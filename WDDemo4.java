package mentor.qa.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WDDemo4 {
		
	public static void main(String[] args) throws Exception {
		
		String[][] data;
		data = excelRead();
		
		HashMap<Double, String> map = new HashMap<Double, String>();
		String itin;
				
		double price;
		for (int i=1; i < data.length; i++) {
			System.out.print("Itin: From " + data[i][0] + " To " + data[i][1] + "     ");
			price = findPriceByTravelocity(data[i][0], data[i][1], data[i][2], data[i][3]);
			System.out.print("Travelocity Price is " + price + "     ");
			itin = " Using Travelocity From : " + data[i][0] + " To : " + data[i][1];
			map.put(price, itin);			
			
			price = findPriceByOrbitz(data[i][0], data[i][1], data[i][2], data[i][3]);
			System.out.println("Orbitz Price is " + price);
			itin = " Using Orbitz From : " + data[i][0] + " To : " + data[i][1];
			map.put(price, itin);
		}
		
		List<Double> list = new ArrayList<Double>(map.keySet());
		Collections.sort(list);
		
		System.out.println("The mimimum fare is " + list.get(0) + " and the itinerary is " + map.get(list.get(0)));
		
	}

	private static double findPriceByTravelocity(String orig, String dest, String stdate, String enddate) {
		
		WebDriver wd = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(wd, 60);
		
		wd.get("http://www.travelocity.com");
		
		wd.findElement(By.xpath("//span[@class='icon icon-flights']")).click();
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("flight-origin")));
				
		wd.findElement(By.id("flight-origin")).clear();
		wd.findElement(By.id("flight-origin")).sendKeys(orig);
		
		wd.findElement(By.id("flight-destination")).clear();
		wd.findElement(By.id("flight-destination")).sendKeys(dest);
		
		wd.findElement(By.id("flight-departing")).clear();
		wd.findElement(By.id("flight-departing")).sendKeys(stdate);
		
		wd.findElement(By.id("flight-returning")).clear();
		wd.findElement(By.id("flight-returning")).sendKeys(enddate);
		
		//wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.findElement(By.id("search-button")).click();
				
		//Thread.sleep(20000);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("xsell-banner-default")));
					
		String price = wd.findElement(By.xpath("//span[@class='dollars price-emphasis bestValue-emphasis bestValueDetails-emphasis']")).getText().replace("$", "").replace(",", "")
				+ wd.findElement(By.xpath("//span[@class='cents secondary price-emphasis bestValue-emphasis bestValueDetails-emphasis']")).getText();
		
		wd.close();
		
		double lprice = Double.parseDouble(price);			
		return lprice;
	}

	public static double findPriceByOrbitz(String orig, String dest, String stdate, String enddate) throws InterruptedException {
		WebDriver wd = new FirefoxDriver();
		WebDriverWait wait1 = new WebDriverWait(wd, 10);
		
		wd.get("http://www.orbitz.com");
		
		wd.findElement(By.xpath("//span[@class='primaryRadioMessage needsclick']")).click();
		
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.name("ar.rt.leaveSlice.orig.key")));
		
		wd.findElement(By.name("ar.rt.leaveSlice.orig.key")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.orig.key")).sendKeys(orig);
		
		wd.findElement(By.name("ar.rt.leaveSlice.dest.key")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.dest.key")).sendKeys(dest);
		
		wd.findElement(By.name("ar.rt.leaveSlice.date")).clear();
		wd.findElement(By.name("ar.rt.leaveSlice.date")).sendKeys(stdate);
		
		wd.findElement(By.name("ar.rt.returnSlice.date")).clear();
		wd.findElement(By.name("ar.rt.returnSlice.date")).sendKeys(enddate);
		
		wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		wd.findElement(By.name("search")).click();
				
		Thread.sleep(20000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultsTwoColumn")));
			
		String price = wd.findElement(By.xpath("//span[@class='money small-cents small-symbol']")).getText().replace("$", "");
		String fprice = price.replace(",","");
		wd.close();
		
		double lprice = Double.parseDouble(fprice);			
		return lprice;
	}
	
	
	public static String[][] excelRead() throws Exception {
		// TODO Auto-generated method stub
		File excel = new File("/Users/HEMA/selenium/Data.xlsx");
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
		//		System.out.println("The value is " + value);				
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
		
		case 0 :
			
			if (DateUtil.isCellDateFormatted(cell)) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				result = sdf.format(cell.getDateCellValue());				
				 
            } else {
            	result = cell.getNumericCellValue();
            }
			
			break;
			
		case 1 :
			result = cell.getStringCellValue();
			break;
			
		default :
			throw new Exception("There is no support for this cell type");
		
		}
		
		return result.toString();
	}
	
	
		
	
}
