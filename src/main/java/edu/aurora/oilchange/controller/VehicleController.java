package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.Main;
import edu.aurora.oilchange.Vehicle;
import edu.aurora.oilchange.ui.AppLauncher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

	@FXML
	private void initialize() {
		// TODO: Use models, fix scaling, fix indentation.
		btnNext.setOnAction(e -> {
			Vehicle vehicle = new Vehicle();
            boolean fail = false;
            String error = "";

            if (Validations.digits(txtMake.getText()).not().any()) {
				vehicle.setMake((txtMake.getText()));
            } else {
                error += "Please Only use letters for the make.";
                fail = true;
			}

			if (Validations.digits(txtModel.getText()).not().any()) {
				vehicle.setModel((txtModel.getText()));
			} else {
				error += " Please Only use letters for the model.";
                fail = true;
			}

			if (Validations.digits(txtYear.getText()).repeat(4)) {
				vehicle.setYear((txtYear.getText()));
			} else {
				error += " Please only use four numbers for the year.";
				fail = true;
			}

			if (dtDate.getValue() != null) {
				vehicle.setDate(dtDate.getValue());
			} else {
				error += " Please enter a valid date.";
				fail = true;
			}

			if (fail) {
				txtWarning.setText(error);
			} else {
				Main.vehicle = vehicle;
				System.out.println(Main.vehicle.getMake());
				AppLauncher.root.setCenter(AppLauncher.oil);
			}
		});
		btnCancel.setOnAction(e -> System.exit(1));
	}
}
