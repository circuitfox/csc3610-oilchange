package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.Main;
import edu.aurora.oilchange.Oil;
import edu.aurora.oilchange.ui.AppLauncher;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

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
	private void initialize() {
		// TODO: Scaling, models, don't throw exceptions
		btnNext.setOnAction(e -> {
			Oil oil = new Oil();
            boolean fail = false;
			String error = "";

			if (Validations.digits(txtOilType.getText()).not().any()) {
				oil.setOilType(txtOilType.getText());
			} else {
				error += "Please only use letters for Oil Type. ";
                fail = true;
			}

			if (Validations.digits(txtOilBrand.getText()).not().any()) {
				oil.setOilBrand(txtOilBrand.getText());
			} else {
				error += "Please only use letters for Oil Brand. ";
                fail = true;
			}

			if (Validations.digits(txtOilQuantity.getText()).some()) {
				oil.setQuantity((Integer.parseInt(txtOilQuantity.getText())));
			} else {
				error += "Please only use numbers for Oil Quantity. ";
                fail = true;
			}

			if (Validations.alphabetical(txtOilPrice.getText()).not().any()) {
                try {
                    Double dubTemp = Double.parseDouble(txtOilPrice.getText());
                    BigDecimal money = new BigDecimal(dubTemp);
                    oil.setPricePerQuart(money);
                } catch (Exception ex) {
                    error += " Enter a valid cost for OilPrice. I.E 1.99 ";
                    fail = true;
                }
			} else {
                error += " Enter a valid cost for OilPrice. I.E 1.99 ";
                fail = true;
			}

			if (Validations.digits(txtFilterBrand.getText()).not().any()) {
				oil.setFilterBrand((txtFilterBrand.getText()));
			} else {
				error += "Please only use letters for Filter Type. ";
                fail = true;
			}

			if (Validations.alphabetical(txtFilterCost.getText()).not().any()) {
                try {
                    Double dubTemp = Double.parseDouble(txtFilterCost.getText());
                    BigDecimal money = new BigDecimal(dubTemp);
                    oil.setFilterCost((money));
                } catch (Exception ex) {
                    error += " Enter a valid cost for Filter Price. I.E 1.99 ";
                    fail = true;
                }
			} else {
                error += " Enter a valid cost for Filter Price. I.E 1.99 ";
                fail = true;
			}

			if (fail) {
				txtWarning.setText(error);
			} else {
				System.out.println("Ready to move to the status screen: ");
				Main.oil = oil;
				AppLauncher.root.setCenter(AppLauncher.summary);
			}
		});
		btnBack.setOnAction(e -> AppLauncher.root.setCenter(AppLauncher.vehicle));
		btnCancel.setOnAction(e -> System.exit(1));
	}
}
