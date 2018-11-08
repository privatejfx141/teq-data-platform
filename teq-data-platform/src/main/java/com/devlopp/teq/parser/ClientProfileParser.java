package com.devlopp.teq.parser;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.devlopp.teq.excel.ExcelReader;

public class ClientProfileParser implements TemplateParser {

    @Override
    public void read(String filePath, int sheetNumber) {
        try {
            ExcelReader.readExcelFile(filePath, sheetNumber);
        } catch (InvalidFormatException | IOException e) {
            
        }
        
    }

    @Override
    public Object create() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
}
