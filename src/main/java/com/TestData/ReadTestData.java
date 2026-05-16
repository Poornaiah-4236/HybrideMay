package com.TestData;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ReadTestData {
	public static String filePath = System.getProperty("user.dir") + File.separator
			+ "src/test/resources/TestData/SampleTestData.xlsx";

	public static String getTestData(String key, String sheetName) throws IOException {
		try (FileInputStream fis = new FileInputStream(filePath); 
				Workbook workbook = WorkbookFactory.create(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new RuntimeException("Sheet not found: " + sheetName);
			}
			Row headerRow = sheet.getRow(0);
			Row dataRow = sheet.getRow(1);
			int dataRows = sheet.getLastRowNum();
			if (headerRow == null || dataRow == null) {
				throw new RuntimeException("Header or Data row is missing");
			}
			int columnIndex = -1;
			// Find column index
			for (int i = 0; i < headerRow.getLastCellNum(); i++) {
				Cell headerCell = headerRow.getCell(i);
				if (headerCell != null && headerCell.getCellType() == CellType.STRING
						&& headerCell.getStringCellValue().equalsIgnoreCase(key)) {
					columnIndex = i;
					break;
				}
			}
			if (columnIndex == -1) {
				throw new RuntimeException("Key not found in Excel: " + key);
			}
			Cell cell = dataRow.getCell(columnIndex);
			if (cell == null) {
				return null;
			}
			// Handle different cell types
			switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue().toString();
				}
				return String.valueOf(cell.getNumericCellValue());
			case BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());
			case FORMULA:
				return cell.getCellFormula();
			default:
				return "";
			}
		}
	}

}
