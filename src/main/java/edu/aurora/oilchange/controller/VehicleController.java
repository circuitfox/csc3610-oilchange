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
			boolean make = false;
			boolean model = false;
			boolean year = false;
			boolean date = false;
			String forDialogue = "";
			if (!txtMake.getText().matches("[0-9]*")) {
				vehicle.setMake((txtMake.getText()));
				make = true;
			}
			if (txtMake.getText().matches("[0-9]+") || txtMake.getText().equals("")) {
				forDialogue = forDialogue + "Please Only use letters for the make.";
				make = false;
			}
			if (!txtModel.getText().matches("[0-9]+")) {
				vehicle.setModel((txtModel.getText()));
				model = true;
			}
			if (txtModel.getText().matches("[0-9]+") || txtModel.getText().equals("")) {
				forDialogue = forDialogue + " Please Only use letters for the model.";
				model = false;
			}
			if (txtYear.getText().matches("\\d{4}")) {
				vehicle.setYear((txtYear.getText()));
				year = true;
			}
			if (!txtYear.getText().matches("\\d{4}")) {
				forDialogue = forDialogue + " Please only use four numbers for the year.";
				year = false;
			}
			if (dtDate.getValue() != null) {
				vehicle.setDate(dtDate.getValue());
				date = true;
			}
			if (dtDate.getValue() == null) {
				forDialogue = forDialogue + " Please enter a valid date.";
				date = false;
			}
			if (!forDialogue.equals("")) {
				txtWarning.setText(forDialogue);
			}
			if (make && model && year && date) {
				Main.vehicle = vehicle;
				System.out.println(Main.vehicle.getMake());
				AppLauncher.root.setCenter(AppLauncher.oil);
			}
		});
		btnCancel.setOnAction(e -> System.exit(1));
	}

}
