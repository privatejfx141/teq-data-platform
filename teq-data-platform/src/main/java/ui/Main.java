package ui;

import com.devlopp.teq.databasehelper.DatabaseDriverHelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	

    public static void main(String[] args) {
        boolean dbExists = DatabaseDriverHelper.databaseExists();
        System.out.println("Database exists: " + dbExists);
        if (!dbExists) {
            DatabaseDriverHelper.initializeDatabase();   
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader
                .load(getClass().getResource("/fxml/start.fxml"));
        primaryStage.setTitle("TEQ data platform");
        primaryStage.setScene(new Scene(root, 700, 800));
        primaryStage.show();        

    }

}
