package com.Utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.io.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Util {

    /**
     * Generic method to write data into Excel
     * @param filePath - Excel file path
     * @param sheetName - Sheet name
     * @param data - List of Map (columnName -> value)
     * @throws IOException 
     * @throws EncryptedDocumentException 
     */
    public static void writeDataToExcel(String filePath, String sheetName, List<Map<String, String>> data) throws EncryptedDocumentException, IOException {
    	FileInputStream fis=new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.createSheet(sheetName);

        if (data == null || data.isEmpty()) {
            System.out.println("No data to write!");
            return;
        }

        // Create Header Row
        Row headerRow = sheet.createRow(0);
        Map<String, String> firstRow = data.get(0);

        int colIndex = 0;
        for (String key : firstRow.keySet()) {
            Cell cell = headerRow.createCell(colIndex++);
            cell.setCellValue(key);
        }

        // Write Data Rows
        int rowIndex = 1;
        for (Map<String, String> rowData : data) {
            Row row = sheet.createRow(rowIndex++);
            colIndex = 0;

            for (String key : firstRow.keySet()) {
                Cell cell = row.createCell(colIndex++);
                cell.setCellValue(rowData.getOrDefault(key, ""));
            }
        }

        // Auto-size columns
        for (int i = 0; i < firstRow.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to file
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            workbook.write(fos);
            workbook.close();
            System.out.println("Data written successfully to Excel!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getData(String query) throws Exception {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/darshan", "root", "Poorna@4236");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        String result = "";
        if (rs.next()) {
            result = rs.getString(1);
        }

        con.close();
        return result;
    }
}
