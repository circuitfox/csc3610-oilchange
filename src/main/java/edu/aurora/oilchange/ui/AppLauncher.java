package edu.aurora.oilchange.ui;

import java.io.IOException;

import edu.aurora.oilchange.controller.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Initializes FXML loaders and controllers and launches the GUI.
 */
public class AppLauncher extends Application {
	private final static int SCALE_2X_HORIZONTAL = 2560;
	private final static int SCALE_2X_VERTICAL = 1440;
	public static BorderPane root;
	public static AnchorPane oil;
	public static AnchorPane vehicle;
	public static AnchorPane summary;
	public static AnchorPane update;

	public void start(Stage primaryStage) throws Exception {
		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
		FXMLLoader vehicleLoader = new FXMLLoader(getClass().getResource("VehicleView.fxml"));
		FXMLLoader oilLoader = new FXMLLoader(getClass().getResource("OilView.fxml"));
		FXMLLoader summaryLoader = new FXMLLoader(getClass().getResource("SummaryView.fxml"));
		FXMLLoader updateLoader = new FXMLLoader(getClass().getResource("UpdateView.fxml"));
		update = updateLoader.load();
		summary = summaryLoader.load();
		vehicle = vehicleLoader.load();
		oil = oilLoader.load();
		root = mainLoader.load();
		root.setCenter(vehicle);
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
