package com.teq.inputCheck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class errorCheck {
    /**
     * Returns an 2D array where index i is the row of input from row i
     * 
     * @throws IOException
     * @throws InvalidFormatException
     * @throws  
     */
    @SuppressWarnings("resource")
    public static ArrayList<ArrayList<String>> parseForDB(String filePath) throws InvalidFormatException, IOException {
        Workbook workbook = null;
        workbook = new XSSFWorkbook(
                new File(filePath));
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        int lastRow = sheet.getLastRowNum();
        ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
        for (Row row : sheet) {
            if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == 1 || row.getRowNum() == lastRow) {
                continue;
            }
            ArrayList<String> cells = new ArrayList<String>();
            int lastColumn = Math.max(row.getLastCellNum(), 5);
            for (int cellNum = 0; cellNum < lastColumn; cellNum++) {
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String input = dataFormatter.formatCellValue(cell);
                cells.add(input);
            }
            // add to db
            // System.out.println(cells.toString());
            output.add(cells);
        }
        return output;
    }

    @SuppressWarnings("resource")
    public static void errorChecking() throws InvalidFormatException, IOException {
        Workbook workbook = null;
        workbook = new XSSFWorkbook(
                new File("/cmshome/sarranch/eclipse_workspace2/inputChecking/src/inputCheck/iCARE_template.xlsx"));
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        HashMap<String, ArrayList<String>> map = createAllowedValues();
        ArrayList<String> headerArray = new ArrayList<String>();
        ArrayList<String> newArray = new ArrayList<String>();
        ;
        System.out.println(map.toString());
        int lastRow = sheet.getLastRowNum();
        for (Row row : sheet) {
            if (row.getRowNum() == 0 || row.getRowNum() == 1 || row.getRowNum() == lastRow) {
                continue;
            }
            ArrayList<String> cells = new ArrayList<String>();
            int lastColumn = 95;// Math.max(row.getLastCellNum(), 5);
            for (int cellNum = 0; cellNum < lastColumn; cellNum++) {
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String input = dataFormatter.formatCellValue(cell);
                // System.out.println(input.isEmpty());
                if (row.getRowNum() == 2) {
                    headerArray.add(input);
                } else {
                    if (map.get(headerArray.get(cellNum)) == null || input.isEmpty()
                            || map.get(headerArray.get(cellNum)).equals(newArray)
                            || map.get(headerArray.get(cellNum)).contains(input)) {
                        // add to db
                        if (map.get(headerArray.get(cellNum)) == null) {
                            System.out.println("added " + input + " wrt " + map.get(headerArray.get(cellNum))
                                    + " header " + headerArray.get(cellNum) + " cell num " + cellNum);
                        }
                        // System.out.println("added " + input + " wrt " +
                        // map.get(headerArray.get(cellNum)) + " header " + headerArray.get(cellNum));
                        cells.add(input);
                    } else {
                        System.out.println("Error, Column " + cellNum + " Row" + " " + row.getRowNum()
                                + " does not contain an allowed value: " + input + " and expected: "
                                + map.get(headerArray.get(cellNum)));
                    }
                }
            }
            // System.out.println(cells.toString());
        }
        // System.out.println(headerArray.toString());
    }

    @SuppressWarnings("resource")
    public static HashMap<String, ArrayList<String>> createAllowedValues() throws InvalidFormatException, IOException {
        Workbook workbook = null;
        workbook = new XSSFWorkbook(new File(
                "/cmshome/sarranch/eclipse_workspace2/inputChecking/src/inputCheck/New_iCARE_Template_Comb_with_Examples.xlsx"));
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
        // errorChecking();
        // parseForDB();
        // System.out.println(map.toString());
    }
}
