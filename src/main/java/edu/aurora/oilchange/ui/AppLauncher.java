package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.controller.OilController;
import edu.aurora.oilchange.controller.SummaryController;
import edu.aurora.oilchange.controller.UpdateController;
import edu.aurora.oilchange.controller.VehicleController;

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

	// TODO: Clean up this.
	public void start(Stage primaryStage) throws Exception {
        // this will be massively cleaned up once the main and add views are done; most of this logic
        // will be in those.
		VehicleModel vehicleModel = new VehicleModel();
		OilModel oilModel = new OilModel();
		OilChangeModel oilChangeModel = new OilChangeModel();
		DateModel dateModel = new DateModel();

		FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("MainView.fxml"));
		FXMLLoader vehicleLoader = new FXMLLoader(getClass().getResource("VehicleView.fxml"));
		FXMLLoader oilLoader = new FXMLLoader(getClass().getResource("OilView.fxml"));
		FXMLLoader summaryLoader = new FXMLLoader(getClass().getResource("SummaryView.fxml"));
		FXMLLoader updateLoader = new FXMLLoader(getClass().getResource("UpdateView.fxml"));

		update = updateLoader.load();
     	UpdateController updateController = updateLoader.getController();
		updateController.setVehicleModel(vehicleModel);
		updateController.setOilModel(oilModel);
		updateController.setOilChangeModel(oilChangeModel);
		updateController.setDateModel(dateModel);

		summary = summaryLoader.load();
		SummaryController summaryController = summaryLoader.getController();
		summaryController.setVehicleModel(vehicleModel);
		summaryController.setOilModel(oilModel);
		summaryController.setOilChangeModel(oilChangeModel);
		summaryController.setDateModel(dateModel);

		vehicle = vehicleLoader.load();
		VehicleController vehicleController = vehicleLoader.getController();
		vehicleController.setVehicleModel(vehicleModel);
		vehicleController.setDateModel(dateModel);

		oil = oilLoader.load();
		OilController oilController = oilLoader.getController();
		oilController.setOilModel(oilModel);

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
