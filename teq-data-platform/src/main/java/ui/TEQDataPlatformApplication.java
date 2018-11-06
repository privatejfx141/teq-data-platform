package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TEQDataPlatformApplication extends Application {

    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TEQDataPlatformApplication.primaryStage = primaryStage;
        Parent root = FXMLLoader
                .load(getClass().getResource("/fxml/home.fxml"));
        primaryStage.setTitle("TEQ data platform");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
