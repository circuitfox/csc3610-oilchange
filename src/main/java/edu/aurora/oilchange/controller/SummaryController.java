package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.Customer;
import edu.aurora.oilchange.Main;
import edu.aurora.oilchange.ui.AppLauncher;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
		btnBack.setOnAction(e -> {
			AppLauncher.root.setCenter(null);
			AppLauncher.root.setCenter(((Node) AppLauncher.oil));
		});
		btnRefresh.setOnAction(e -> {
			lblMake.setText(Main.vehic.getMake());
			lblModel.setText(Main.vehic.getModel());
			lblYear.setText(Main.vehic.getYear());
			lblDate.setText(Main.vehic.getDate().toString());
			lblOilType.setText(Main.oil.getOilType());
			lblOilBrand.setText(Main.oil.getOilBrand());
			lblOilCost.setText(Main.oil.getPricePerQuart().toString());
			lblOilAmount.setText(String.valueOf(Main.oil.getQuantity()));
			lblFilterBrand.setText(Main.oil.getFilterBrand());
			lblFilterCost.setText(Main.oil.getFilterCost().toString());
		});
		btnSave.setOnAction(e -> {
			int id = (int) (Math.random() * 999999);
			Main.cust = new Customer(Main.vehic, Main.oil, id);
			System.out.println("Information stored. Customer ID is " + id);
		});

	}

}
