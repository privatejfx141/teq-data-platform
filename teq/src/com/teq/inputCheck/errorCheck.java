package inputCheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class errorCheck {

public static void main(String[] args) throws IOException {
	
	errorChecking("/cmshome/sarranch/cscc01_space/Team14/teq/testUser.xlsx");
	
	}
	

	/** Returns an array list of allowed values for the given header, header
	 * @param header: column header that you want the allowed values for
	 */
	public static ArrayList<String> allowedValues(String header) {
		HashMap<String, ArrayList<String>> allowedValuesArray = new HashMap<>();
		//allowedValues.put(db.getColumn(header))
		// add db code when you get access to it
		// temp code:
		//if allowedValues.containsKey(header){
		//	allowedValues.get(header).add("Coloumn Header")
		//} else {
		ArrayList<String> newArray = new ArrayList<String>();
		newArray.add("Bob");
		allowedValuesArray.put("Namheadere", newArray);
		//}
		return newArray;
	}
	
	
	/** Returns false if the file contains errors, otherwise true, and will print the column,
	 *  row and value for any incorrect inputed cells
	 * @param filePath: full path to the excel file which you want to be error checked
	 */
	public static Boolean errorChecking(String filePath) throws IOException {
			
        //FileInputStream inputStream = new FileInputStream(new File(filePath));
		
		// errors == false when no errors in input, else true
		Boolean errors = false;
		
		// open workbook from file path and move to sheet 0
        Workbook workbook = new XSSFWorkbook(filePath);
        Sheet firstSheet = workbook.getSheetAt(0);
        
        // create an iterator for each row and data formatter to read input 
        Iterator<Row> iterator = firstSheet.iterator();
        DataFormatter dataFormatter = new DataFormatter();
        
        // Create an array to store all the headers in the excel file
        ArrayList<String> headerArray = new ArrayList<String>();
        
        // Create a variable that will turn false after iterating through
        // column in the first row, so that we can collect each header
        Boolean firstRow = true;
        
        // for each row...
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            
            // for each column...
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            int colCounter = 0;
            while (cellIterator.hasNext()) {
            	// get the input in each cell, and read it
                Cell cell = cellIterator.next();
                String input = dataFormatter.formatCellValue(cell);
                
                // check if the data exists as an allowed value using allowedValues()
                if (firstRow == true) {
                	headerArray.add(input);
                } else if (allowedValues(headerArray.get(colCounter)).contains(input) ) {
                
                // if it doesn't print the column, row and input where is not allowed
                } else {
                	errors = true;
                	System.out.println("Error, Column " + cell.getColumnIndex() + " Row"
                						+ " "+ cell.getRowIndex() + " does not contain an allowed value: " + input);
                }
                colCounter ++;
            }
            firstRow = false;
            System.out.println();
        }
        // close workbook and input streams
        workbook.close();
        //inputStream.close();
        // return whether or not errors where found
		return errors;
	}
	
	
}
