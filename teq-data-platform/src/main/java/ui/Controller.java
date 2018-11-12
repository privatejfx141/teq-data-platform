package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.devlopp.teq.databasehelper.DatabaseInsertHelper;
import com.devlopp.teq.parser.TemplateParser;
import com.devlopp.teq.parser.TemplateParserFactory;

public class Controller {
    @FXML
    private ComboBox<String> templateTypes;
    @FXML
    private Button addFileButton;

    public Controller() {
    }

    public String getFileExtension(File file) {
        String fileName = file.getName();
        int delimIndex = fileName.lastIndexOf('.');
        String ext = fileName.substring(delimIndex + 1, fileName.length());
        return ext.toLowerCase();
    }

    public void handleAddFileAction(ActionEvent actionEvent) {
        String templateType = templateTypes.getValue();
        if (templateType != null) {
            FileChooser chooser = new FileChooser();
            File dataFile = chooser.showOpenDialog(new Stage());
            // if data file was selected
            if (dataFile != null) {
                // if data file was of Excel
                String ext = getFileExtension(dataFile);
                if (ext.startsWith("xls")) {
                    insertData(dataFile.getPath(), templateType);
                } else {
                    System.out.println("File must be an Excel file!");
                }
            }
        }
    }

    public void insertData(String filePath, String templateType) {
        System.out.println("File path: " + filePath);
        System.out.println("Template:  " + templateType);
        TemplateParser templateParser = TemplateParserFactory.getParser(templateType);
        templateParser.read(filePath, 0);
        for (Object record: templateParser.parse()) {
            Object recordId = DatabaseInsertHelper.insertRecord(record);
            System.out.println("Template type: " + templateType);
            System.out.println("New record ID: " + recordId);
        }
    }
}
