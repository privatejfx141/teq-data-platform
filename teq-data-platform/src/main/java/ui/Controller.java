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
        File dataFile = chooser.showOpenDialog(new Stage());
    }
}
