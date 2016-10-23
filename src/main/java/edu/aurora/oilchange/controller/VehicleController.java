package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.ui.AppLauncher;
import edu.aurora.oilchange.ui.DateModel;
import edu.aurora.oilchange.ui.VehicleModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class VehicleController {
	@FXML
	private Button btnNext;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField txtMake;
	@FXML
	private TextField txtModel;
	@FXML
	private TextField txtYear;
	@FXML
	private DatePicker dtDate;
	@FXML
	private TextArea txtWarning;

	private VehicleModel vehicleModel;
	private DateModel dateModel;

	@FXML
	private void initialize() {
        dtDate.setValue(LocalDate.of(dateModel.getYear(), dateModel.getMonth(), dateModel.getDay()));

		txtMake.textProperty().bind(vehicleModel.makeProperty());
        txtModel.textProperty().bind(vehicleModel.modelProperty());
		txtYear.textProperty().bind(vehicleModel.yearProperty());

		dtDate.valueProperty().addListener((observable, oldValue, newValue) -> {
			dateModel.setMonth(newValue.getDayOfMonth());
			dateModel.setDay(newValue.getDayOfMonth());
			dateModel.setYear(newValue.getYear());
		});

		btnNext.setOnAction(e -> {
            boolean fail = false;
            String error = "";

            if (Validations.digits(txtMake.getText()).any()) {
                error += "Please Only use letters for the make.";
                fail = true;
			}

			if (Validations.digits(txtModel.getText()).any()) {
				error += " Please Only use letters for the model.";
                fail = true;
			}

			if (!Validations.digits(txtYear.getText()).repeat(4)) {
				error += " Please only use four numbers for the year.";
				fail = true;
			}

			if (fail) {
				txtWarning.setText(error);
			} else {
				AppLauncher.root.setCenter(AppLauncher.oil);
			}
		});
		btnCancel.setOnAction(e -> System.exit(1));
	}

	public void setVehicleModel(VehicleModel model) {
		this.vehicleModel = model;
	}

	public void setDateModel(DateModel model) {
		this.dateModel = model;
	}
}
