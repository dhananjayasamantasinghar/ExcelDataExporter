package com.realspeed.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.realspeed.model.Data;

public class ExcelDataExporter {

	/**
	 * 
	 * Write Java Object Lists to Excel File
	 * 
	 * @param customers
	 * @param filePath
	 * @throws IOException
	 */
	public static String writeObjects2ExcelFile(List<Data> dataList, File tempDir) throws IOException {
		String[] columns = { "Issue No", "Issue Type", "Assignee", "Reporter", "Created Date" };
		Workbook workbook = null;
		FileOutputStream fileOut = null;

		try {
			workbook = new XSSFWorkbook();

			Sheet sheet = workbook.createSheet("Data");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < columns.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(columns[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CreationHelper createHelper = workbook.getCreationHelper();
			// CellStyle ageCellStyle = workbook.createCellStyle();
			// ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			int rowIdx = 1;
			for (Data data : dataList) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(data.getIssueNo());
				row.createCell(1).setCellValue(data.getIssueType());
				row.createCell(2).setCellValue(data.getAssignee());
				row.createCell(3).setCellValue(data.getReporter());
				row.createCell(4).setCellValue(data.getCreatedDate());
			}
			fileOut = new FileOutputStream(tempDir+"\\data.xlsx");
			workbook.write(fileOut);
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		} finally {
			if (fileOut != null)
				fileOut.close();
			if (workbook != null)
				workbook.close();
		}
		return "Success";
	}
}
