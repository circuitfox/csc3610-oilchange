package edu.aurora.oilchange.controller;

import java.math.BigDecimal;

import edu.aurora.oilchange.Main;
import edu.aurora.oilchange.Oil;
import edu.aurora.oilchange.ui.AppLauncher;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OilController {
	@FXML
	private TextField txtOilType;
	@FXML
	private TextField txtOilBrand;
	@FXML
	private TextField txtOilQuantity;
	@FXML
	private TextField txtOilPrice;
	@FXML
	private TextField txtFilterCost;
	@FXML
	private TextField txtFilterBrand;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnNext;
	@FXML
	private TextArea txtWarning;

	@FXML
	private void initialize() throws NumberFormatException {
		btnNext.setOnAction(e -> {
			Oil oil = new Oil();
			boolean oilType = false, oilBrand = false, oilQuant = false, oilPrice = false, filterType = false,
					filterBrand = false;
			String whatsWrong = "";
			if (!txtOilType.getText().toString().matches("[0-9]+")) {
				oil.setOilType((txtOilType.getText().toString()));
				oilType = true;
			}
			if (!txtOilBrand.getText().toString().matches("[0-9]+")) {
				oil.setOilBrand(txtOilBrand.getText().toString());
				oilBrand = true;
			}
			if (txtOilQuantity.getText().toString().matches("[0-9]+")) {
				oil.setQuantity((Integer.parseInt(txtOilQuantity.getText().toString())));
				oilQuant = true;

			}
			if (!txtOilPrice.getText().isEmpty()) {
				String temp = ((txtOilPrice.getText().toString()));
				if (temp.contains(".")) {
					try {
						Double dubTemp = Double.parseDouble(temp);
						BigDecimal money = new BigDecimal(dubTemp);
						oil.setPricePerQuart(money);
						oilPrice = true;
					} catch (Exception excep) {
						whatsWrong = whatsWrong + " Only use a decimal number";
					}

				} else
					whatsWrong = whatsWrong + " Enter a valid cost for OilPrice. I.E 1.99";

			}
			if (!txtFilterBrand.getText().toString().matches("[0-9]+")) {
				oil.setFilterBrand((txtFilterBrand.getText().toString()));
				filterBrand = true;
			}
			if (!txtFilterCost.getText().isEmpty()) {
				String temp = ((txtFilterCost.getText().toString()));
				if (temp.contains(".")) {
					try {
						Double dubTemp = Double.parseDouble(temp);
						BigDecimal money = new BigDecimal(dubTemp);
						oil.setFilterCost((money));
						filterType = true;
					} catch (Exception excep) {
						whatsWrong = whatsWrong + " Enter a valid cost for Filter Price. I.E 1.99";
					}

				} else
					whatsWrong = whatsWrong + " Enter a valid cost for Filter Price. I.E 1.99";
			}
			if (txtOilType.getText().toString().matches("[0-9]+") || txtOilType.getText().isEmpty()) {
				whatsWrong = whatsWrong + "Please only use letters for Oil Type. ";
				oilType = false;
			}
			if (txtOilBrand.getText().toString().matches("[0-9]+") || txtOilBrand.getText().isEmpty()) {
				whatsWrong = whatsWrong + "Please only use letters for Oil Brand. ";
				oilBrand = false;
			}

			if (!txtOilQuantity.getText().toString().matches("[0-9]+") || txtOilQuantity.getText().isEmpty()) {
				whatsWrong = whatsWrong + "Please only use numbers for Oil Quantity. ";
				oilQuant = false;
			}

			if (txtOilPrice.getText().isEmpty()) {
				whatsWrong = whatsWrong + "Please fill in Oil Price field. ";
				oilPrice = false;
			}

			if (txtFilterBrand.getText().toString().matches("[0-9]+") || txtFilterBrand.getText().isEmpty()) {
				whatsWrong = whatsWrong + "Please only use letters for Filter Type. ";
				filterBrand = false;
			}

			if (txtFilterCost.getText().isEmpty()) {
				whatsWrong = whatsWrong + "Please fill in Filter Cost field. ";
				filterType = false;
			}
			if (!whatsWrong.equals(""))
				txtWarning.setText(whatsWrong);
			if (oilType && oilBrand && oilQuant && oilPrice && filterType && filterBrand) {
				System.out.println("Ready to move to the status screen: ");
				Main.oil = oil;
				AppLauncher.root.setCenter(null);
				AppLauncher.root.setCenter(((Node) AppLauncher.summary));

			}
		});
		btnBack.setOnAction(e -> {
			AppLauncher.root.setCenter(null);
			AppLauncher.root.setCenter(((Node) AppLauncher.vehicle));
		});
		btnCancel.setOnAction(e -> {
			System.exit(1);
		});
	}
}
