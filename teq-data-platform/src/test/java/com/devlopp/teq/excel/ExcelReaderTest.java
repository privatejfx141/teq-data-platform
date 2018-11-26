package com.devlopp.teq.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExcelReaderTest {

    static ArrayList<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
    String FILEPATH_TEMPLATES = "src/test/java/com/devlopp/teq/excel/iCARE_Templates.xlsx";
    String FILEPATH_TEST_FILE = "src/test/java/com/devlopp/teq/excel/TestTemplate.xlsx";

    @BeforeAll
    static public void initialize() {
        test = new ArrayList<ArrayList<String>>();
        ArrayList<String> one = new ArrayList<String>();
        one.add("Processing Details");
        one.add("Unique Identifier");
        one.add("Unique Identifier Value");
        one.add("Date of Birth (YYYY-MM-DD)");
        one.add("Phone Number");
        one.add("Does the Client Have an Email Address");

        ArrayList<String> two = new ArrayList<String>();
        two.add("");
        two.add("FOSS/GCMS Client ID");
        two.add("12345678");
        two.add("1978-05-20");
        two.add("902-628-1285");
        two.add("Yes");
        ArrayList<String> three = new ArrayList<String>();
        three.add("");
        three.add("FOSS/GCMS");
        three.add("123wer8");
        three.add("1978-23-21");
        three.add("992-628-1285");
        three.add("No");

        test.add(one);
        test.add(two);
        test.add(three);
    }

    @Test
    @DisplayName("readExcelFile gives appropriate multi-line output with missing fields in file")
    public void multiLinedEmptyFieldsTest() throws InvalidFormatException, IOException {
        ArrayList<ArrayList<String>> output = ExcelReader.readExcelFile(FILEPATH_TEST_FILE, 0);

        assertEquals(output, test);

    }

    @Test
    @DisplayName("errorChecking gives appropriate error with bad entries")
    void errorCheckBadEntriesTest() {
        String output = "";
        try {
            output = ExcelReader.errorChecking(1, FILEPATH_TEST_FILE, 2);
        } catch (InvalidFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals(output,
                "Error, Column 1 Row 3 does not contain an allowed value:"
                        + " FOSS/GCMS Client ID1 and expected: [FOSS/GCMS Client ID, Temporary Resident"
                        + " or Minister’s Permit Number, IMM5292, IMM5509, IMM1000 Number]\n");
    }

    @Test
    @DisplayName("errorChecking gives appropriate error with correct entry")
    void errorCheckGoodEntryTest() {

        String output = "";
        try {
            output = ExcelReader.errorChecking(5, FILEPATH_TEMPLATES, 5);
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

        String output = "";
        try {
            output = ExcelReader.errorChecking(2, FILEPATH_TEMPLATES, 2);
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

        String output = "";
        try {
            output = ExcelReader.errorChecking(3, FILEPATH_TEMPLATES, 3);
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

        String output = "";
        try {
            output = ExcelReader.errorChecking(4, FILEPATH_TEMPLATES, 4);
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

        String output = "";
        try {
            output = ExcelReader.errorChecking(5, FILEPATH_TEMPLATES, 5);
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
    @DisplayName("errorChecking good entry for Template 6 ")
    void errorCheckTemplate6GoodTest() {

        String output = "";
        try {
            output = ExcelReader.errorChecking(6, FILEPATH_TEMPLATES, 6);
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
    @DisplayName("errorChecking good entry for Template 7 ")
    void errorCheckTemplate7GoodTest() {

        String output = "";
        try {
            output = ExcelReader.errorChecking(7, FILEPATH_TEMPLATES, 7);
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
    @DisplayName("errorChecking good entry for Template 8 ")
    void errorCheckTemplate8GoodTest() {

        String output = "";
        try {
            output = ExcelReader.errorChecking(8, FILEPATH_TEMPLATES, 8);
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
    @DisplayName("errorChecking good entry for Template 9 ")
    void errorCheckTemplate9GoodTest() {

        String output = "";
        try {
            output = ExcelReader.errorChecking(9, FILEPATH_TEMPLATES, 9);
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
    @DisplayName("errorChecking with empty mandatory column  ")
    void errorCheckMandatoryColumnTest() {

        String output = "";
        try {
            output = ExcelReader.errorChecking(3, FILEPATH_TEST_FILE, 2);
        } catch (InvalidFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertEquals(output,
                "Error, Column 1 Row 3 does not contain an allowed value:  and expected: [FOSS/GCMS Client ID, Temporary Resident or Minister’s Permit Number, IMM5292, IMM5509, IMM1000 Number]\n");
    }

}
