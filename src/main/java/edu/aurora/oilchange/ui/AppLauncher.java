package edu.aurora.oilchange.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Initializes FXML loaders and controllers and launches the GUI.
 */
public class AppLauncher extends Application {
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("main.fxml"));
        root.setCenter(mainLoader.load());

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
