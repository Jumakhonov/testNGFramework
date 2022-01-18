package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	public static String pathToExcelfile = ".\\src\\test\\resources\\inputData\\Data.xlsx";

	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getExcelData(String sheetName) {

		try {
			FileInputStream file = new FileInputStream(pathToExcelfile);
			book = WorkbookFactory.create(file);
			sheet = book.getSheet(sheetName);

			// This line of code will read all values in excel till last row and last column
			int rowSize = sheet.getLastRowNum(), colSize=sheet.getRow(0).getLastCellNum();
			
			Object data[][] = new Object[rowSize][colSize];
			System.out.println(data.length +"      "+ data[0].length);
			for (int row = 0; row < rowSize; row++) {
				for (int col = 0; col < colSize; col++) {
				//	System.out.print(sheet.getRow(row).getCell(col).toString() + " ");
					data[row][col] = sheet.getRow(row).getCell(col).getStringCellValue();
				}

			}
			return data;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}


}
