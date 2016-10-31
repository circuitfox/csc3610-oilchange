package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.ui.DateModel;
import edu.aurora.oilchange.ui.OilChangeModel;
import edu.aurora.oilchange.ui.OilModel;
import edu.aurora.oilchange.ui.VehicleModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController {

    @FXML
    private BorderPane pane;

    @FXML
    private HBox hbVehicle;

    @FXML
    private HBox hbOil;

    @FXML
    private HBox hbSummary;

    @FXML
    private Button btnNext;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCancel;

    private VehicleModel vehicleModel;
    private OilModel oilModel;
    private OilChangeModel oilChangeModel;
    private DateModel dateModel;

    private AddStage currentStage;
    private boolean onOilStage;

    private AddVehicleController vehicleController;
    private AddOilDefaultController oilDefaultController;
    private AddOilManualController oilManualController;
    private AddSummaryController summaryController;

    public AddController() {
        vehicleModel = new VehicleModel();
        oilModel = new OilModel();
        oilChangeModel = new OilChangeModel();
        dateModel = new DateModel();
    }

    @FXML
    private void initialize() {
        System.out.println("AddController init");
        loadStage(AddStage.VEHICLE);
        hbVehicle.getStyleClass().add("stage");
        currentStage = AddStage.VEHICLE;

        btnNext.setOnAction(e -> {
            switch(currentStage) {
                case VEHICLE:
                    if (vehicleController.validate()) {
                        hbVehicle.getStyleClass().remove("stage");
                        hbOil.getStyleClass().add("stage");
                        loadStage(AddStage.OIL);
                        currentStage = AddStage.OIL;
                    }
                    break;
                case OIL:
                    // check against default controller first
                    if (oilManualController.validate()) {
                        hbOil.getStyleClass().remove("stage");
                        hbSummary.getStyleClass().add("stage");
                        loadStage(AddStage.SUMMARY);
                        btnNext.setText("OK");
                        currentStage = AddStage.SUMMARY;
                    }
                    break;
                case SUMMARY:
                    // TODO: DB call here
                    closeStage();
                    break;
            }
        });

        btnBack.setOnAction(e -> {
            switch (currentStage) {
                case VEHICLE:
                    break;
                case OIL:
                    hbOil.getStyleClass().remove("stage");
                    hbVehicle.getStyleClass().add("stage");
                    loadStage(AddStage.VEHICLE);
                    currentStage = AddStage.VEHICLE;
                    break;
                case SUMMARY:
                    hbSummary.getStyleClass().remove("stage");
                    hbOil.getStyleClass().add("stage");
                    loadStage(AddStage.OIL);
                    btnNext.setText("Next");
                    currentStage = AddStage.OIL;
                    break;
            }
        });

        btnCancel.setOnAction(e -> closeStage());
    }

    private enum AddStage {
        VEHICLE,
        OIL,
        SUMMARY
    }

    private void handleLoadingError(String fileName) {
        Alert error = new Alert(Alert.AlertType.ERROR,
                "An IOException has occurred loading " + fileName + ", the add view must close.");
        error.showAndWait().ifPresent(r -> {
            if (r == ButtonType.OK) {
                closeStage();
            }
        });

    }

    private void closeStage() {
        ((Stage)pane.getScene().getWindow()).close();
    }

    private void loadStage(AddStage stage) {
        FXMLLoader loader;
        AnchorPane currentPane = new AnchorPane();
        switch (stage) {
            case VEHICLE:
                loader = new FXMLLoader(getClass().getResource("/edu/aurora/oilchange/ui/AddVehicleView.fxml"));
                try {
                    currentPane = loader.load();
                } catch (IOException ex) {
                    // this should ideally never happen
                    handleLoadingError("/edu/aurora/oilchange/ui/AddVehicleView.fxml");
                }
                vehicleController = loader.getController();
                vehicleController.setVehicleModel(vehicleModel);
                vehicleController.setDateModel(dateModel);
                pane.setCenter(currentPane);
                break;
            case OIL:
                // TODO: Recommend oil lookup
                // If we had a selected option, then go to that screen first.
                //if (VehicleMake.hasOil(vehicleModel.getMake()) && !onOilStage) {
                //
                //} else {
                loader = new FXMLLoader(getClass().getResource("/edu/aurora/oilchange/ui/AddOilManualView.fxml"));
                try {
                    currentPane = loader.load();
                } catch (IOException ex) {
                    handleLoadingError("/edu/aurora/oilchange/ui/AddOilManualView.fxml");
                }
                oilManualController = loader.getController();
                oilManualController.setOilModel(oilModel);
                pane.setCenter(currentPane);
                break;
            case SUMMARY:
                loader = new FXMLLoader(getClass().getResource("/edu/aurora/oilchange/ui/AddSummaryView.fxml"));
                try {
                    currentPane = loader.load();
                } catch (IOException ex) {
                    handleLoadingError("/edu/aurora/oilchange/ui/AddSummaryView.fxml");
                }
                summaryController = loader.getController();
                summaryController.setVehicleModel(vehicleModel);
                summaryController.setOilModel(oilModel);
                summaryController.setOilChangeModel(oilChangeModel);
                summaryController.setDateModel(dateModel);
                pane.setCenter(currentPane);
                break;
        }
    }
}
