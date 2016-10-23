package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.ui.DateModel;
import edu.aurora.oilchange.ui.OilChangeModel;
import edu.aurora.oilchange.ui.OilModel;
import edu.aurora.oilchange.ui.VehicleModel;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private VehicleModel vehicleModel;
    private OilModel oilModel;
    private OilChangeModel oilChangeModel;
    private DateModel dateModel;

    @FXML
    private void initialize() {
        // TODO: TextFormatter validation
        NumberStringConverter numberStringConverter = new NumberStringConverter();
        BigDecimalStringConverter bigDecimalStringConverter = new BigDecimalStringConverter();
        DecimalFormat currencyFormat = new DecimalFormat("#0.00");

        dtDate.setValue(LocalDate.of(dateModel.getYear(), dateModel.getMonth(), dateModel.getDay()));

        txtMake.textProperty().bind(vehicleModel.makeProperty());
        txtModel.textProperty().bind(vehicleModel.modelProperty());
        txtYear.textProperty().bind(vehicleModel.yearProperty());

        dtDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            dateModel.setMonth(newValue.getDayOfMonth());
            dateModel.setDay(newValue.getDayOfMonth());
            dateModel.setYear(newValue.getYear());
        });

        txtOilType.textProperty().bind(oilModel.oilTypeProperty());
        txtOilBrand.textProperty().bind(oilModel.oilBrandProperty());
        txtOilQuantity.textProperty().bindBidirectional(oilModel.quantityProperty(), numberStringConverter);
        txtOilPrice.textProperty().bindBidirectional(oilModel.pricePerQuartProperty(), bigDecimalStringConverter);
        txtFilterBrand.textProperty().bind(oilModel.filterBrandProperty());
        txtFilterCost.textProperty().bindBidirectional(oilModel.filterCostProperty(), bigDecimalStringConverter);

        lblCost.textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal total = oilModel.getPricePerQuart()
                            .multiply(BigDecimal.valueOf(oilModel.getQuantity()))
                            .add(oilModel.getFilterCost()).add(oilChangeModel.getTotal());
                    return "$" + currencyFormat.format(total);
                }, oilModel.quantityProperty(), oilModel.pricePerQuartProperty(),
                oilModel.filterCostProperty(), oilChangeModel.laborHoursProperty()));

        btnOk.setOnAction(e -> {
            boolean fail = false;
            String error = "";

            if (Validations.digits(txtMake.getText()).any()) {
                error += "Please Only use letters for the make.";
                fail = true;
            }

            if (Validations.digits(txtModel.getText()).any()) {
                error += " Please Only use letters for the model.";
                fail = true;
            }

            if (!Validations.digits(txtYear.getText()).repeat(4)) {
                error += " Please only use four numbers for the year.";
                fail = true;
            }

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
                System.err.println(error);
            } else {
                // FIXME: Send DB command
            }
        });

        btnCancel.setOnAction(e -> ((Stage)btnCancel.getScene().getWindow()).close());
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setOilModel(OilModel oilModel) {
        this.oilModel = oilModel;
    }

    public void setOilChangeModel(OilChangeModel oilChangeModel) {
        this.oilChangeModel = oilChangeModel;
    }

    public void setDateModel(DateModel dateModel) {
        this.dateModel = dateModel;
    }
}
