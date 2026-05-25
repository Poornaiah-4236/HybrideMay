package com.TestData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {
	public static String filePath = System.getProperty("user.dir") + File.separator
			+ "src/test/resources/TestData/SampleTestData.xlsx";
	public static List<String> getColumnValues(String sheetName, String headerName) {
        List<String> values = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);
            int colIndex = -1;

            // ✅ Step 1: Find column index by header name
            for (int col = 0; col < headerRow.getLastCellNum(); col++) {
                if (headerRow.getCell(col).getStringCellValue().trim().equalsIgnoreCase(headerName)) {
                    colIndex = col;
                    break;
                }
            }

            if (colIndex == -1) {
                throw new RuntimeException("Header not found: " + headerName);
            }

            // ✅ Step 2: Read values under that column
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);

                if (row == null) continue;

                Cell cell = row.getCell(colIndex);
                String value = getCellValue(cell);

                // ✅ Skip empty cells
                if (!value.isEmpty()) {
                    values.add(value);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return values;
    }


    // Helper to skip truly empty rows
	private static String getCellValue(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

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