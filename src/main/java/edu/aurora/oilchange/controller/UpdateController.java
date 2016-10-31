package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.VehicleMake;
import edu.aurora.oilchange.ui.DateModel;
import edu.aurora.oilchange.ui.OilChangeModel;
import edu.aurora.oilchange.ui.OilModel;
import edu.aurora.oilchange.ui.VehicleModel;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class UpdateController {
    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<String> cbMake;
    @FXML
    private TextField txtMake;
    @FXML
    private ComboBox<String> cbModel;
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
    private Label lblDigitError;
    @FXML
    private Label lblLetterError;
    @FXML
    private Label lblPriceError;

    private VehicleModel vehicleModel;
    private OilModel oilModel;
    private OilChangeModel oilChangeModel;
    private DateModel dateModel;

    public UpdateController() {
        vehicleModel = new VehicleModel();
        oilModel = new OilModel();
        oilChangeModel = new OilChangeModel();
        dateModel = new DateModel();
    }

    @FXML
    private void initialize() {
        // TODO: TextFormatter validation
        dtDate.setValue(LocalDate.of(dateModel.getYear(), dateModel.getMonth(), dateModel.getDay()));
        cbMake.getItems().setAll(VehicleMake.stringValues());
        cbMake.valueProperty().addListener((observable, oldValue, newValue) -> {
            VehicleMake value = VehicleMake.fromString(newValue);
            if (value == VehicleMake.OTHER) {
                cbModel.setDisable(true);
            } else {
                cbModel.setDisable(false);
                cbModel.getItems().setAll(VehicleMake.vehicleMap.get(value).split(", "));
                cbModel.getItems().add("Other");
                vehicleModel.setMake(newValue);
            }
        });

        cbModel.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equalsIgnoreCase("Other")) {
                vehicleModel.setModel(newValue);
            }
        });

        txtMake.visibleProperty().bind(Bindings.equal(cbMake.valueProperty(), "Other"));
        txtModel.visibleProperty().bind(Bindings
                .equal(cbMake.valueProperty(), "Other")
                .or(Bindings.equal(cbModel.valueProperty(), "Other")));

        bindVehicle();
        bindDate();
        bindOil();
        bindTotalCost();

        btnOk.setOnAction(e -> {
            boolean digitsValid = true;
            boolean lettersValid = true;
            boolean costValid = true;

            if (!Validations.digits(txtYear.getText()).repeat(4)) {
                lblDigitError.setText(lblDigitError.getText() + "the year (4 digits)");
                lblDigitError.setVisible(true);
                digitsValid = false;
            }

            if (Validations.digits(txtOilType.getText()).any()) {
                lblLetterError.setText(lblLetterError.getText() + "the oil type");
                lblLetterError.setVisible(true);
                lettersValid = false;
            }

            if (Validations.digits(txtOilBrand.getText()).any()) {
                if (!lettersValid) {
                    lblLetterError.setText(lblLetterError.getText() + " and the oil brand");
                } else {
                    lblLetterError.setText(lblLetterError.getText() + "the oil brand");
                }
                lblLetterError.setVisible(true);
                lettersValid = false;
            }

            if (!Validations.digits(txtOilQuantity.getText()).some()) {
                if (!digitsValid) {
                    lblDigitError.setText(lblDigitError.getText() + " and the oil quantity");
                } else {
                    lblDigitError.setText(lblDigitError.getText() + "the oil quantity");
                }
                lblDigitError.setVisible(true);
                digitsValid = false;
            }

            if (Validations.alphabetical(txtOilPrice.getText()).any()) {
                lblPriceError.setText(lblPriceError.getText() + "the oil price");
                lblPriceError.setVisible(true);
                costValid = false;
            }

            if (Validations.digits(txtFilterBrand.getText()).any()) {
                if (!lettersValid) {
                    lblLetterError.setText(lblLetterError.getText() + " and the filter brand");
                }
                lblLetterError.setVisible(true);
                lettersValid = false;
            }

            if (Validations.alphabetical(txtFilterCost.getText()).any()) {
                if (!costValid) {
                    lblPriceError.setText(lblPriceError.getText() + " and the filter price");
                } else {
                    lblPriceError.setText(lblPriceError.getText() + "the filter price");
                }
                lblPriceError.setVisible(true);
                costValid = false;
            }

            if (digitsValid && lettersValid && costValid) {
                // FIXME: Send DB command
            }
        });

        btnCancel.setOnAction(e -> ((Stage)btnCancel.getScene().getWindow()).close());
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
        bindVehicle();
    }

    public void setOilModel(OilModel oilModel) {
        this.oilModel = oilModel;
        bindOil();
        bindTotalCost();
    }

    public void setOilChangeModel(OilChangeModel oilChangeModel) {
        this.oilChangeModel = oilChangeModel;
        bindTotalCost();
    }

    public void setDateModel(DateModel dateModel) {
        this.dateModel = dateModel;
        bindDate();
    }

    private void bindVehicle() {
        txtMake.textProperty().bindBidirectional(vehicleModel.makeProperty());
        txtModel.textProperty().bindBidirectional(vehicleModel.modelProperty());
        txtYear.textProperty().bindBidirectional(vehicleModel.yearProperty());
    }

    private void bindDate() {
        dtDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            dateModel.setMonth(newValue.getMonthValue());
            dateModel.setDay(newValue.getDayOfMonth());
            dateModel.setYear(newValue.getYear());
        });
    }

    private void bindOil() {
        NumberStringConverter numberStringConverter = new NumberStringConverter();
        BigDecimalStringConverter bigDecimalStringConverter = new BigDecimalStringConverter();

        txtOilType.textProperty().bindBidirectional(oilModel.oilTypeProperty());
        txtOilBrand.textProperty().bindBidirectional(oilModel.oilBrandProperty());
        txtOilQuantity.textProperty().bindBidirectional(oilModel.quantityProperty(), numberStringConverter);
        txtOilPrice.textProperty().bindBidirectional(oilModel.pricePerQuartProperty(), bigDecimalStringConverter);
        txtFilterBrand.textProperty().bindBidirectional(oilModel.filterBrandProperty());
        txtFilterCost.textProperty().bindBidirectional(oilModel.filterCostProperty(), bigDecimalStringConverter);
    }

    private void bindTotalCost() {
        DecimalFormat currencyFormat = new DecimalFormat("#0.00");
        lblCost.textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal total = oilModel.getPricePerQuart()
                            .multiply(BigDecimal.valueOf(oilModel.getQuantity()))
                            .add(oilModel.getFilterCost()).add(oilChangeModel.getTotal());
                    return "$" + currencyFormat.format(total);
                }, oilModel.quantityProperty(), oilModel.pricePerQuartProperty(),
                oilModel.filterCostProperty(), oilChangeModel.laborHoursProperty()));
    }
}
