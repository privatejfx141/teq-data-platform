package com.devlopp.teq.parser;

import java.util.List;

public interface TemplateParser {
    
    public void read(String filePath, int sheetNumber);
    
    public List<Object> parse();
    
}
