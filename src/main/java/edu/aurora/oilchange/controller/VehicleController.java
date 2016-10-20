package edu.aurora.oilchange.controller;

import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

import edu.aurora.oilchange.Main;
import edu.aurora.oilchange.Vehicle;
import edu.aurora.oilchange.ui.AppLauncher;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
		btnNext.setOnAction(e -> {
			Vehicle vehic = new Vehicle();
			boolean make = false;
			boolean model = false;
			boolean year = false;
			boolean date = false;
			String forDialouge = "";
			if (!txtMake.getText().toString().matches("[0-9]*")) {
				vehic.setMake((txtMake.getText()).toString());
				make = true;
			}
			if (txtMake.getText().toString().matches("[0-9]+") || txtMake.getText().toString().equals("")) {
				forDialouge = forDialouge + "Please Only use letters for the make.";
				make = false;
			}
			if (!txtModel.getText().toString().matches("[0-9]+")) {
				vehic.setModel((txtModel.getText().toString()));
				model = true;
			}
			if (txtModel.getText().toString().matches("[0-9]+") || txtModel.getText().toString().equals("")) {
				forDialouge = forDialouge + " Please Only use letters for the model.";
				model = false;
			}
			if (txtYear.getText().toString().matches("\\d{4}")) {
				vehic.setYear((txtYear.getText().toString()));
				year = true;
			}
			if (!txtYear.getText().toString().matches("\\d{4}")) {
				forDialouge = forDialouge + " Please only use four numbers for the year.";
				year = false;
			}
			if (dtDate.getValue() != null) {
				vehic.setDate(dtDate.getValue());
				date = true;
			}
			if (dtDate.getValue() == null) {
				forDialouge = forDialouge + " Please enter a valid date.";
				date = false;
			}
			if (!forDialouge.equals("")) {
				txtWarning.setText(forDialouge);
			}
			if (make && model && year && date) {
				Main.vehic = vehic;
				System.out.println(Main.vehic.getMake());
				AppLauncher.root.setCenter(null);
				AppLauncher.root.setCenter(((Node) AppLauncher.oil));
			}
		});
		btnCancel.setOnAction(e -> {
			System.exit(1);
		});
	}

}
