package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.VehicleMake;
import edu.aurora.oilchange.ui.DateModel;
import edu.aurora.oilchange.ui.VehicleModel;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class AddVehicleController {
    @FXML
    private Button btnNext;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<String> cbMake;
    @FXML
    private TextField txtMake;
    @FXML
    private ComboBox<String> cbModel;
    @FXML
    private TextField txtModel;
    @FXML
    private TextField txtYear;
    @FXML
    private DatePicker dtDate;
    @FXML
    private Label lblDateError;

    private VehicleModel vehicleModel;
    private DateModel dateModel;

    public AddVehicleController() {
        vehicleModel = new VehicleModel();
        dateModel = new DateModel();
    }

    @FXML
    private void initialize() {
        dtDate.setValue(LocalDate.of(dateModel.getYear(), dateModel.getMonth(), dateModel.getDay()));
        cbMake.getItems().setAll(VehicleMake.stringValues());
        cbMake.valueProperty().addListener((observable, oldValue, newValue) -> {
            VehicleMake value = VehicleMake.fromString(newValue);
            if (value == VehicleMake.OTHER) {
                cbModel.setDisable(true);
            } else {
                cbModel.setDisable(false);
                cbModel.getItems().setAll(VehicleMake.vehicleMap.get(value).split(", "));
                cbModel.getItems().add("Other");
                vehicleModel.setMake(newValue);
            }
        });

        cbModel.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equalsIgnoreCase("Other")) {
                vehicleModel.setModel(newValue);
            }
        });

        txtMake.visibleProperty().bind(Bindings.equal(cbMake.valueProperty(), "Other"));
        txtModel.visibleProperty().bind(Bindings
                .equal(cbMake.valueProperty(), "Other")
                .or(Bindings.equal(cbModel.valueProperty(), "Other")));

        bindVehicle();
        bindDate();
    }

    public void setVehicleModel(VehicleModel model) {
        this.vehicleModel = model;
        bindVehicle();
    }

    public void setDateModel(DateModel model) {
        this.dateModel = model;
        bindDate();
    }

    public boolean validate() {
        boolean valid = true;

        if (!Validations.digits(txtYear.getText()).repeat(4)) {
            lblDateError.setVisible(true);
            valid = false;
        }

        if (valid) {
            lblDateError.setVisible(false);
        }
        return valid;
    }

    private void bindVehicle() {
        cbMake.setValue(vehicleModel.getMake());
        cbModel.setValue(vehicleModel.getModel());
        txtMake.textProperty().bindBidirectional(vehicleModel.makeProperty());
        txtModel.textProperty().bindBidirectional(vehicleModel.modelProperty());
        txtYear.textProperty().bindBidirectional(vehicleModel.yearProperty());
    }

    private void bindDate() {
        dtDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            dateModel.setMonth(newValue.getMonthValue());
            dateModel.setDay(newValue.getDayOfMonth());
            dateModel.setYear(newValue.getYear());
        });
    }
}
