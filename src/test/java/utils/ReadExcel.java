package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {
	public static String cellValue = null;
	final static String FILE_LOCATION = "./src/main/resources/resources/TestData.xlsx";
	public static DecimalFormat format = new DecimalFormat("0.#");
	
	public ReadExcel() {
		
	}

	public static String readByColumnName(String sheetName, String columnName, int rowNum) throws Exception { 
		try {

			FileInputStream fileInputStream = new FileInputStream(FILE_LOCATION);
			Workbook workbook = WorkbookFactory.create(fileInputStream);
			Sheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(0);
			short lastcolumnused = row.getLastCellNum();

			int colnum = 0;
			for (int i = 0; i < lastcolumnused; i++) {
				if (row.getCell(i).getStringCellValue().equalsIgnoreCase(columnName)) {
					colnum = i;
					break;
				}
			}
			
			try {

				row = sheet.getRow(rowNum);
				Cell column = row.getCell(colnum);

				if (column.getCellType() == CellType.STRING) {
					cellValue = column.getStringCellValue();

				} else if (column.getCellType() == CellType.NUMERIC) {
					double cellVall = column.getNumericCellValue();
					cellValue = (format.format(cellVall));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return cellValue;
	}

	public static List<String> readAllBySheet(String sheetName) {
		List<String> list = new ArrayList<String>();

		try {
			FileInputStream inputStream = new FileInputStream(new File("./data/TestData.xlsx"));
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheet("Topics");
			int firstRow = sheet.getFirstRowNum();
			int lastRow = sheet.getLastRowNum();

			for (int index = firstRow + 1; index <= lastRow; index++) {
				Row row = sheet.getRow(index);

				for (int cellIndex = row.getFirstCellNum(); cellIndex < row.getLastCellNum(); cellIndex++) {
					Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					list.add(cell.getStringCellValue());
				}
			}

			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
