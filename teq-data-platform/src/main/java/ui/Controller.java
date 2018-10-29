package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    public Controller() {
    }

    @FXML
    public void handleAddFileAction(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();
        File dataFile = chooser.showOpenDialog(new Stage());
    }
}
