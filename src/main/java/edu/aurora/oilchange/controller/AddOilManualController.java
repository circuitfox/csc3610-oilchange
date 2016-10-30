package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.ui.OilModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.NumberStringConverter;

public class AddOilManualController {
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
    private Label lblQuantityError;
    @FXML
    private Label lblPriceError;
    @FXML
    private Label lblFilterCostError;
    @FXML
    private Label lblFilterBrandError;
    @FXML
    private Label lblTypeError;
    @FXML
    private Label lblBrandError;

    private OilModel oilModel;

    public AddOilManualController() {
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
    }

    public void setOilModel(OilModel model) {
        this.oilModel = model;
    }

    public boolean validate() {
        boolean valid = true;

        if (Validations.digits(txtOilType.getText()).any()) {
            lblTypeError.setVisible(true);
            valid = false;
        }

        if (Validations.digits(txtOilBrand.getText()).any()) {
            lblBrandError.setVisible(true);
            valid = false;
        }

        if (!Validations.digits(txtOilQuantity.getText()).some()) {
            lblQuantityError.setVisible(true);
            valid = false;
        }

        if (Validations.alphabetical(txtOilPrice.getText()).any()) {
            lblPriceError.setVisible(true);
            valid = false;
        }

        if (Validations.digits(txtFilterBrand.getText()).any()) {
            lblFilterBrandError.setVisible(true);
            valid = false;
        }

        if (Validations.alphabetical(txtFilterCost.getText()).any()) {
            lblFilterCostError.setVisible(true);
            valid = false;
        }

        if (valid) {
            lblQuantityError.setVisible(false);
            lblPriceError.setVisible(false);
            lblFilterCostError.setVisible(false);
            lblFilterBrandError.setVisible(false);
            lblTypeError.setVisible(false);
            lblBrandError.setVisible(false);
        }
        return valid;
    }
}
