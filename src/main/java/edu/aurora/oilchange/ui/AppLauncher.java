package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.controller.MainController;
import edu.aurora.oilchange.db.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Initializes FXML loaders and controllers and launches the GUI.
 */
public class AppLauncher extends Application {
    private final static int SCALE_2X_HORIZONTAL = 2560;
    private final static int SCALE_2X_VERTICAL = 1440;

    public void start(Stage primaryStage) {
        Properties dbProperties = new Properties();
        BorderPane root = new BorderPane();
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));

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

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("css/common.css").toExternalForm());
        scale(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // scale to 2x for displays >= SCALE_2X_HORIZONTAL and SCALE_2X_VERTICAL
    private void scale(Scene scene) {
        Rectangle2D bounds = Screen.getPrimary().getBounds();
        String scaleStylesheet;
        if (bounds.getWidth() >= SCALE_2X_HORIZONTAL && bounds.getHeight() >= SCALE_2X_VERTICAL) {
            scaleStylesheet = "qhd.css";
        } else {
            scaleStylesheet = "fhd.css";
        }
        scene.getStylesheets().add(getClass().getResource("css/" + scaleStylesheet).toExternalForm());
    }
}
