package com.devlopp.teq.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExcelReaderTest {
	
		ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
		
		@BeforeAll public void initialize() {
	    	test = new ArrayList<ArrayList<String>>();
	    	ArrayList<String> one = new ArrayList<String>();
	    	one = (ArrayList<String>) Arrays.asList("Processing Details	Unique Identifier",	"Unique Identifier Value",	"Date of Birth (YYYY-MM-DD)",	"Phone Number",	"Does the Client Have an Email Address");
	    	ArrayList<String> two = new ArrayList<String>();
	    	two = (ArrayList<String>) Arrays.asList("",	"FOSS/GCMS Client ID", "12345678",	"1978-05-20",	"902-628-1285",	"Yes");
	    	ArrayList<String> three = new ArrayList<String>();
	    	three = (ArrayList<String>) Arrays.asList("",	"FOSS/GCMS ", "123wer8",	"1978-23-21",	"992-628-1285",	"No");
	    	ArrayList<String> four = new ArrayList<String>();
	    	four = (ArrayList<String>) Arrays.asList("", "", "", "", "", "", "");
	    	test.add(one);
	    	test.add(two);
	    	test.add(three);
		}
	    
	    
	    @Test
		@DisplayName("readExcelFile gives appropriate multi-line output with missing fields in file")
		public void multiLinedEmptyFieldsTest() throws InvalidFormatException, IOException {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\TestTemplate.xlsx";
			ArrayList<ArrayList<String>>  output =ExcelReader.readExcelFile(v,0);

	    	assertEquals(output, test);

		}
/*
	    @Test
		@DisplayName("readExcelFile with wrong sheet number")
		void MediumTruckNoOptionTest() {
	    	Offer output =new Offer("truck", "medium", new String[0]);
	    	assertEquals(b.toString(), "[medium] truck");
	    	assertEquals(b.getPrice(), 60, 0.01);
		}
	    
	    @Test
		@DisplayName("readExcelFile with wrong file path")
		void MediumTruckNoOptionTest() {
	    	Offer output =new Offer("truck", "medium", new String[0]);
	    	assertEquals(b.toString(), "[medium] truck");
	    	assertEquals(b.getPrice(), 60, 0.01);
		}
	    
	    */
	   
	    
	    @Test
		@DisplayName("errorChecking gives appropriate error with bad entries")
		void errorCheckBadEntriesTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\TestTemplate.xlsx";
	    	String output ="";
			try {
				output =ExcelReader.errorChecking(0, v, 2);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, false);
		}
	    
	    @Test
		@DisplayName("errorChecking gives appropriate error with wrong template")
		void errorCheckWrongTemplateUploadedTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\iCare_Template.xlsx";
	    	String output ="";
			try {
				output =ExcelReader.errorChecking(1, v, 2);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, false);
		}
	    
	    @Test
		@DisplayName("errorChecking gives appropriate error with correct entry")
		void errorCheckGoodEntryTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\iCare_Template.xlsx";
	    	String output ="";
			try {
				output =ExcelReader.errorChecking(5, v, 5);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, "");
		}
	/*
	    @Test
		@DisplayName("errorChecking with wrong sheet number")
		void MediumTruckNoOptionTest() {
	    	Offer output =new Offer("truck", "medium", new String[0]);
	    	assertEquals(b.toString(), "[medium] truck");
	    	assertEquals(b.getPrice(), 60, 0.01);
		}
	    
	    @Test
		@DisplayName("errorChecking with wrong file path")
		void MediumTruckNoOptionTest() {
	    	Offer output =new Offer("truck", "medium", new String[0]);
	    	assertEquals(b.toString(), "[medium] truck");
	    	assertEquals(b.getPrice(), 60, 0.01);
		}
	    */
	    
	    @Test
		@DisplayName("errorChecking good entry for Template 1 ")
		void errorCheckTemplate1GoodTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\iCare_Template.xlsx";
	    	String output ="";
			try {
				output =ExcelReader.errorChecking(1, v, 1);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, "");
		}
		
	    @Test
		@DisplayName("errorChecking good entry for Template 2 ")
		void errorCheckTemplate2GoodTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\iCare_Template.xlsx";
	    	String output ="";
			try {
				output =ExcelReader.errorChecking(2, v, 2);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, "");
		}
	    
	    @Test
		@DisplayName("errorChecking good entry for Template 3 ")
		void errorCheckTemplate3GoodTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\iCare_Template.xlsx";
	    	String output ="";
			try {
				output =ExcelReader.errorChecking(3, v, 3);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, "");
		}
	    
	    @Test
		@DisplayName("errorChecking good entry for Template 4 ")
		void errorCheckTemplate4GoodTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\iCare_Template.xlsx";
	    	String output = "";
			try {
				output = ExcelReader.errorChecking(4, v, 4);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, "");
		}
	    

	    
	    @Test
		@DisplayName("errorChecking good entry for Template 5 ")
		void errorCheckTemplate5GoodTest() {
	    	String v = "\\src\\test\\java\\com\\devlopp\\teq\\excel\\iCare_Template.xlsx";
	    	String output = "";
			try {
				output = ExcelReader.errorChecking(5, v, 5);
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	assertEquals(output, "");
		}
}
