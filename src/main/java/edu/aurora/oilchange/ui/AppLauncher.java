package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.controller.MainController;
import edu.aurora.oilchange.db.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Initializes FXML loaders and controllers and launches the GUI.
 */
public class AppLauncher extends Application {
    public void start(Stage primaryStage) {
        Properties dbProperties = new Properties();
        BorderPane root = new BorderPane();
        FXMLLoader mainLoader = UiUtils.getView("MainView.fxml");

        try {
            dbProperties.load(getClass().getResourceAsStream("/edu/aurora/oilchange/db.properties"));
        } catch (IOException ex) {
            System.err.println("Could not load database properties file");
            ex.printStackTrace();
            System.exit(1);
        }

        Database database = new Database(dbProperties);
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try {
            root = mainLoader.load();
        } catch (IOException ex) {
            System.err.println("Failed to load Main view.");
            ex.printStackTrace();
            System.exit(1);
        }

        MainController mainController = mainLoader.getController();
        mainController.setDatabase(database);
        mainController.setThreadPool(threadPool);
        mainController.runTableUpdateService();

        Scene scene = UiUtils.getScene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
