package edu.aurora.oilchange.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Initializes FXML loaders and controllers and launches the GUI.
 */
public class AppLauncher extends Application {
	private final static int SCALE_2X_HORIZONTAL = 2560;
	private final static int SCALE_2X_VERTICAL = 1440;

	public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        try {
            root = mainLoader.load();
        } catch (IOException ex) {
            System.err.println("Failed to load Main view.");
            ex.printStackTrace();
        }

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
