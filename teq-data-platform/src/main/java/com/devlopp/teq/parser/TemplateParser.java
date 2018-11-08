package com.devlopp.teq.parser;

public interface TemplateParser {
    
    public void read(String filePath, int sheetNumber);
    
    public Object create();
    
}
