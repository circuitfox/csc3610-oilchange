package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.ui.AppLauncher;
import edu.aurora.oilchange.ui.OilModel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.NumberStringConverter;

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

	private OilModel oilModel;

    public OilController() {
        oilModel = new OilModel();
    }

	@FXML
	private void initialize() {
        // TODO: Use TextFormatter for validation
        NumberStringConverter numberStringConverter = new NumberStringConverter();
        BigDecimalStringConverter bigDecimalStringConverter = new BigDecimalStringConverter();

        // We need to have bidirectional bindings to convert String to Number/BigDecimal
        txtOilType.textProperty().bindBidirectional(oilModel.oilTypeProperty());
        txtOilBrand.textProperty().bindBidirectional(oilModel.oilBrandProperty());
        txtOilQuantity.textProperty().bindBidirectional(oilModel.quantityProperty(), numberStringConverter);
        txtOilPrice.textProperty().bindBidirectional(oilModel.pricePerQuartProperty(), bigDecimalStringConverter);
        txtFilterBrand.textProperty().bindBidirectional(oilModel.filterBrandProperty());
        txtFilterCost.textProperty().bindBidirectional(oilModel.filterCostProperty(), bigDecimalStringConverter);

        btnNext.setOnAction(e -> {
            boolean fail = false;
			String error = "";

			if (Validations.digits(txtOilType.getText()).any()) {
				error += "Please only use letters for Oil Type. ";
                fail = true;
			}

			if (Validations.digits(txtOilBrand.getText()).any()) {
				error += "Please only use letters for Oil Brand. ";
                fail = true;
			}

			if (!Validations.digits(txtOilQuantity.getText()).some()) {
				error += "Please only use numbers for Oil Quantity. ";
                fail = true;
			}

			if (Validations.alphabetical(txtOilPrice.getText()).any()) {
                error += " Enter a valid cost for OilPrice. I.E 1.99 ";
                fail = true;
			}

			if (Validations.digits(txtFilterBrand.getText()).any()) {
				error += "Please only use letters for Filter Type. ";
                fail = true;
			}

			if (Validations.alphabetical(txtFilterCost.getText()).any()) {
                error += " Enter a valid cost for Filter Price. I.E 1.99 ";
                fail = true;
			}

			if (fail) {
				txtWarning.setText(error);
			} else {
				AppLauncher.root.setCenter(AppLauncher.summary);
			}
		});
		btnBack.setOnAction(e -> AppLauncher.root.setCenter(AppLauncher.vehicle));
		btnCancel.setOnAction(e -> System.exit(1));
	}

	public void setOilModel(OilModel model) {
        this.oilModel = model;
    }
}
