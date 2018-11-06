package ui;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    public Controller() {
    }

    public void handleAddFileAction(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        Stage primary = TEQDataPlatformApplication.primaryStage;
        FileChooser.ExtensionFilter excelFilter =
                new FileChooser.ExtensionFilter("Excel files", "*.xls", "*.xlsx");
        chooser.getExtensionFilters().add(excelFilter);
        File template = chooser.showOpenDialog(primary);
    }
}
