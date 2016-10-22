package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.Customer;
import edu.aurora.oilchange.Main;
import edu.aurora.oilchange.ui.AppLauncher;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SummaryController {
	@FXML
	private Button btnBack;
	@FXML
	private Label lblMake;
	@FXML
	private Label lblModel;
	@FXML
	private Label lblYear;
	@FXML
	private Label lblDate;
	@FXML
	private Label lblOilType;
	@FXML
	private Label lblOilBrand;
	@FXML
	private Label lblOilCost;
	@FXML
	private Label lblFilterBrand;
	@FXML
	private Label lblFilterCost;
	@FXML
	private Label lblOilAmount;
	@FXML
	private Button btnRefresh;
	@FXML
	private Button btnSave;

	@FXML
	private void initialize() {
		// TODO: use models
		btnBack.setOnAction(e -> {
			AppLauncher.root.setCenter(AppLauncher.oil);
		});
		btnRefresh.setOnAction(e -> {
			lblMake.setText(Main.vehicle.getMake());
			lblModel.setText(Main.vehicle.getModel());
			lblYear.setText(Main.vehicle.getYear());
			lblDate.setText(Main.vehicle.getDate().toString());
			lblOilType.setText(Main.oil.getOilType());
			lblOilBrand.setText(Main.oil.getOilBrand());
			lblOilCost.setText(Main.oil.getPricePerQuart().toString());
			lblOilAmount.setText(String.valueOf(Main.oil.getQuantity()));
			lblFilterBrand.setText(Main.oil.getFilterBrand());
			lblFilterCost.setText(Main.oil.getFilterCost().toString());
		});
		btnSave.setOnAction(e -> {
			int id = (int) (Math.random() * 999999);
			Main.customer = new Customer(Main.vehicle, Main.oil, id);
			System.out.println("Information stored. Customer ID is " + id);
		});

	}

}
