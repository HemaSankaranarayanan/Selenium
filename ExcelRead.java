package mentor.qa.selenium;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	public static void main(String[] args) throws Exception {
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
				System.out.println("The value is " + value);
				
			}
			
		}

		wb.close();
	}

	public static String cellToString(XSSFCell cell) throws Exception {
		// TODO Auto-generated method stub
		int type;
		Object result;
		type = cell.getCellType();
		
		switch (type) {
		
		case 0 :
			result = cell.getNumericCellValue();
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
