package edu.aurora.oilchange.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UpdateController {
	@FXML
	private Button btnOk;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField txtMake;
	@FXML
	private TextField txtModel;
	@FXML
	private TextField txtYear;
	@FXML
	private TextField txtOilType;
	@FXML
	private TextField txtOilBrand;
	@FXML
	private TextField txtOilQuantity;
	@FXML
	private TextField txtOilPrice;
	@FXML
	private TextField txtFilterBrand;
	@FXML
	private TextField txtFilterCost;
	@FXML
	private DatePicker dtDate;
	@FXML
	private Label lblCost;

    @FXML
    private void initialize() {
		// TODO: Use models, validation, etc.
    	btnOk.setOnAction(e -> {
    		
    	});
    }
}
