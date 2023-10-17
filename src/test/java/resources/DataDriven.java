package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import stepDefinitions.CRUDStepDefinitions;

public class DataDriven {


	int column = 0;
	int k = 0;
	private static Logger log=LogManager.getLogger(DataDriven.class.getName());

	public ArrayList<Object> getDataFromExcel(String sheetName, String testCaseName) throws IOException {

		ArrayList<Object> a = new ArrayList<Object>();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\TestData.xlsx");

		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// Get the sheets count and go to desired sheet
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// Get access to all the rows and cells in a sheet
				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
				Row firstRow = rows.next();

				Iterator<Cell> cell = firstRow.cellIterator(); // row is collection of cells
				while (cell.hasNext()) {
					Cell value = cell.next();
					if (value.getStringCellValue().equalsIgnoreCase("TestCase")) {

						// getting column index of 'TestCase' header
						column = k;
					}
					k++;
				}

				// Once the 'TestCase' column identified , scan the entire TestCase column find
				// the actual testcase row
			
				while (rows.hasNext()) {
					Row row = rows.next();
					String cval = row.getCell(column).getStringCellValue();

					if (cval.equalsIgnoreCase(testCaseName)) {

						// grab all the cells of the row
						Iterator<Cell> cellValue = row.cellIterator();
						while (cellValue.hasNext()) {

							// assign as per data type and add and return as array list
							Cell c = cellValue.next();
							if (c.getCellType() == CellType.STRING) {
								a.add(c.getStringCellValue());
							} else if (c.getCellType() == CellType.BOOLEAN) {
								a.add(c.getBooleanCellValue());
							}

						}

					}
				}

			}

		}
		log.info("List of values from excel"+a);
		return a;
	}

}
