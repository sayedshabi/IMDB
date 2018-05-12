package com.imdb.helper;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	/**
	 * This method is to set the File path and to open the Excel file, Pass Excel
	 * Path and Sheet name as Arguments to this method
	 * 
	 * @param Path
	 * @param SheetName
	 * @throws Exception
	 */
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * Method for getting cell data for the excel file provided
	 * 
	 * @param fileName
	 * @param sheetName
	 * @param rowNum
	 * @param columnNum
	 * @return
	 * @throws IOException
	 */
	public static String getDataFromExcelSheet(String fileName, String sheetName, int rowNum, int columnNum)
			throws IOException {
		FileInputStream ExcelFile = new FileInputStream(fileName);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		Cell = ExcelWSheet.getRow(rowNum).getCell(columnNum);
		String CellData = Cell.getStringCellValue();
		return CellData;

	}
}
