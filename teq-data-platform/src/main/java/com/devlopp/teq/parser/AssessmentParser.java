package com.devlopp.teq.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.excel.ExcelReader;
import com.devlopp.teq.service.assessment.AssessmentBuilder;
import com.devlopp.teq.service.assessment.IAssessmentBuilder;

public class AssessmentParser implements TemplateParser {
    private ArrayList<ArrayList<String>> data;
    private int numRecords = 0;
    
    public AssessmentParser() {
    }
    
    @Override
    public void read(String filePath, int sheetNumber) {
        try {
            data = ExcelReader.parseForDB(sheetNumber, filePath);
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> parse() {
        IAssessmentBuilder builder = new AssessmentBuilder();
        builder.setClientId(0);
        return null;
    }
}
