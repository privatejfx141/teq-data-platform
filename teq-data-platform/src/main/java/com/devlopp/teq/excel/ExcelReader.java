package com.devlopp.teq.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    public static String INFO_FILEPATH = "src/main/java/com/devlopp/teq/Excel/iCARE_Templates.xlsx";

    /**
     * Returns an 2D array where index i is the row of input from row i
     * 
     * @throws IOException
     * @throws InvalidFormatException
     */
    @SuppressWarnings("resource")
    public static ArrayList<ArrayList<String>> readExcelFile(String filePath, int sheetNumber)
            throws InvalidFormatException, IOException {
        Workbook workbook = null;
        workbook = new XSSFWorkbook(new File(filePath));
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        DataFormatter dataFormatter = new DataFormatter();
        int lastRow = sheet.getLastRowNum();
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0 || row.getRowNum() == 1 ) {
                continue;
            }
            if (isRowEmpty(row)) {
                System.out.println("Reached empty row");
                break;
            }
            ArrayList<String> cells = new ArrayList<String>();
            int lastColumn = row.getLastCellNum();
            for (int cellNum = 0; cellNum < lastColumn; cellNum++) {
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String input = dataFormatter.formatCellValue(cell);
                cells.add(input);
            }
            output.add(cells);
        }
        workbook.close();
        return output;
    }

    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK)
                return false;
        }
        return true;
    }

    @SuppressWarnings("resource")
    public static Boolean errorChecking(int sheetNumber, String filePath, int template)
            throws InvalidFormatException, IOException {
        HashMap<String, ArrayList<String>> map = createAllowedValues();
        ArrayList<String> allHeaders = getAllHeaders(template);
        Workbook workbook = null;
        workbook = new XSSFWorkbook(new File(filePath));
        Sheet sheet = workbook.getSheetAt(sheetNumber);
        DataFormatter dataFormatter = new DataFormatter();
        Boolean noErrors = true;
        ArrayList<String> mandatoryColumns = getMandatoryColumns();
        ArrayList<String> headerArray = new ArrayList<String>();
        ArrayList<String> newArray = new ArrayList<String>();
        // for each row,
        for (Row row : sheet) {
            // skip the first and second rows
            if (row.getRowNum() == 0 || row.getRowNum() == 1) {
                continue;
            }
            if (isRowEmpty(row)) {
                break;
            }
            ArrayList<String> cells = new ArrayList<String>();
            int lastColumn = 309;// getNumColumn(template);
            for (int cellNum = 0; cellNum < lastColumn; cellNum++) {
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String input = dataFormatter.formatCellValue(cell);
                // get every header in template
                if (row.getRowNum() == 2) {
                    headerArray.add(input);
                } else {
                    if (map.get(headerArray.get(cellNum)) == null || map.get(headerArray.get(cellNum)).equals(newArray)
                            || map.get(headerArray.get(cellNum)).contains(input)) {
                        System.out.println(allHeaders.toString());
                        System.out.println(headerArray.toString());
                        if (mandatoryColumns.contains(headerArray.get(cellNum)) && input.isEmpty()) {
                            noErrors = false;
                            System.out.println("Error, Column " + cellNum + " Row" + " " + row.getRowNum()
                                    + " is mandatory, but is not filled in");
                        } else if (allHeaders.contains(headerArray.get(cellNum)) == false
                                && !(headerArray.get(cellNum).equals(""))) {
                            System.out.println(
                                    "Error, " + headerArray.get(cellNum) + " is not a column in the selected template");
                        } else {
                            cells.add(input);
                        }
                    } else {
                        System.out.println("Error, Column " + cellNum + " Row" + " " + row.getRowNum()
                                + " does not contain an allowed value: " + input + " and expected: "
                                + map.get(headerArray.get(cellNum)));
                        noErrors = false;
                    }
                }
            }
        }
        workbook.close();
        return noErrors;
    }

    private static ArrayList<String> getMandatoryColumns() throws InvalidFormatException, IOException {
        ArrayList<String> mandatoryColumns = new ArrayList<>();
        mandatoryColumns.add("Unique Identifier");
        mandatoryColumns.add("Unique Identifier Value");
        mandatoryColumns.add("Date of Birth (YYYY-MM-DD)");
        mandatoryColumns.add("Postal Code where the service was received");
        mandatoryColumns.add("Language of Service");
        mandatoryColumns.add("Official Language of Preference");
        mandatoryColumns.add("Referred By");
        mandatoryColumns.add("Activity Under Which Client Received Services");
        mandatoryColumns.add("Type of Institution/Organization Where Client Received Services");
        mandatoryColumns.add("Main Topic/Focus of the Service Received");
        mandatoryColumns.add("Service Received");
        mandatoryColumns.add("Status of Service");
        mandatoryColumns.add("Start Date (YYYY-MM-DD)");
        mandatoryColumns.add("Was Essential Skills and Aptitudes Training Received as Part of the Service?");
        mandatoryColumns.add("Support Services Received");
        mandatoryColumns.add("Start Date of Service (YYYY-MM-DD)");
        mandatoryColumns.add(
                "Was Life Skills or Responsibilities of Citizenship Information Received as Part of this Service?");
        mandatoryColumns.add("End Date of Service (YYYY-MM-DD)");
        mandatoryColumns.add("Start Date of Assessment (YYYY-MM-DD)");
        mandatoryColumns.add("Client intends to become a Canadian citizen?");
        mandatoryColumns.add("Support services may be required");
        mandatoryColumns.add("Non-IRCC program services needed");
        mandatoryColumns.add("Settlement Plan completed and shared with client");
        mandatoryColumns.add("End Date of Assessment (YYYY-MM-DD)");
        mandatoryColumns.add("Postal Code");
        mandatoryColumns.add("Consent for Future Research/Consultation");
        mandatoryColumns.add("Registration in an employment intervention");
        mandatoryColumns.add("Course Code");
        mandatoryColumns.add("Date of Client's First Class (YYYY-MM-DD)");
        mandatoryColumns.add("Course Held On An Ongoing Basis");
        mandatoryColumns.add("Format of Training Provided");
        mandatoryColumns.add("Total Number of Spots in Course");
        mandatoryColumns.add("Number of IRCC-Funded Spots in Course");
        mandatoryColumns.add("New Students Can Enrol in the Course");
        mandatoryColumns.add("Support Services Available for Client in this Course");
        mandatoryColumns.add("Course Start Date (YYYY-MM-DD)");
        mandatoryColumns.add("Course End Date (YYYY-MM-DD)");
        mandatoryColumns.add("Instructional Hours Per Class");
        mandatoryColumns.add("Classes Per Week");
        mandatoryColumns.add("Dominant Focus of the Course");
        mandatoryColumns.add("Course Directed at a Specific Target Group");
        mandatoryColumns.add("Materials Used in Course");
        mandatoryColumns.add("Client's Training Status");
        return mandatoryColumns;
    }

    private static ArrayList<String> getAllHeaders(int template) throws InvalidFormatException, IOException {
        ArrayList<String> allHeaders = new ArrayList<>();
        Workbook workbook = null;
        workbook = new XSSFWorkbook(new File(INFO_FILEPATH));
        Sheet sheet = workbook.getSheetAt(template);
        DataFormatter dataFormatter = new DataFormatter();
        Boolean noErrors = true;
        // for each row,
        for (Row row : sheet) {
            // skip the first and second rows
            if (row.getRowNum() == 0 || row.getRowNum() == 1) {
                continue;
            }
            if (isRowEmpty(row)) {
                break;
            }
            ArrayList<String> cells = new ArrayList<String>();
            int lastColumn = 309;// getNumColumn(template);
            for (int cellNum = 0; cellNum < lastColumn; cellNum++) {
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String input = dataFormatter.formatCellValue(cell);
                // get every header in template
                if (row.getRowNum() == 2) {
                    if (input.isEmpty() == false) {
                        allHeaders.add(input);
                    }
                }
            }
        }
        workbook.close();
        return allHeaders;
    }

    @SuppressWarnings("resource")
    public static HashMap<String, ArrayList<String>> createAllowedValues() throws InvalidFormatException, IOException {
        Workbook workbook = null;
        workbook = new XSSFWorkbook(new File(INFO_FILEPATH));
        Sheet sheet = workbook.getSheetAt(1);
        DataFormatter dataFormatter = new DataFormatter();
        HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
        ArrayList<String> headerArray = new ArrayList<String>();
        for (int cellNum = 0; cellNum < 311; cellNum++) {
            ArrayList<String> cells = new ArrayList<String>();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                if (row.getRowNum() == 1) {
                    headerArray.add(dataFormatter.formatCellValue(sheet.getRow(1).getCell(cellNum)));
                    continue;
                }
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                if (cell == null) {
                    continue;
                } else if (cell.getColumnIndex() == 0) {
                    // pass
                } else {
                    String input = dataFormatter.formatCellValue(cell);
                    cells.add(input);
                }
            }
            map.put(headerArray.get(cellNum), cells);
        }
        workbook.close();
        return map;
    }

    public static void main(String[] args) throws InvalidFormatException, IOException {
        // createAllowedValues();
        // errorChecking(2,
        // "/cmshome/sarranch/Documents/Team14/teq-data-platform/src/main/java/com/devlopp/teq/inputCheck/New_iCARE_Template_Comb_with_Examples.xlsx",
        // 2);
        // getAllHeaders(2);
        // parseForDB(2,
        // "/cmshome/sarranch/Documents/Team14/teq-data-platform/src/main/java/com/devlopp/teq/inputCheck/New_iCARE_Template_Comb_with_Examples.xlsx");
    }
}
