package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.OilType;
import edu.aurora.oilchange.VehicleMake;
import edu.aurora.oilchange.db.Database;
import edu.aurora.oilchange.db.DatabaseValueService;
import edu.aurora.oilchange.ui.*;

import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.BigDecimalStringConverter;
import javafx.util.converter.NumberStringConverter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;

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
    private ComboBox<String> cbType;
    @FXML
    private ComboBox<String> cbKind;
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

    private Database database;
    private ExecutorService threadPool;
    private DatabaseValueService tableUpdateService;

    private CustomerModel customerModel;
    private OilChangeModel oilChangeModel;

    public UpdateController() {
        customerModel = new CustomerModel();
        oilChangeModel = new OilChangeModel();
    }

    @FXML
    private void initialize() {
        // TODO: TextFormatter validation
        dtDate.setValue(LocalDate.of(
                customerModel.getDate().getYear(),
                customerModel.getDate().getMonth(),
                customerModel.getDate().getDay()));
        cbMake.getItems().setAll(VehicleMake.stringValues());
        cbMake.valueProperty().addListener((observable, oldValue, newValue) -> {
            VehicleMake value = VehicleMake.fromString(newValue);
            if (value == VehicleMake.OTHER) {
                cbModel.setDisable(true);
            } else {
                cbModel.setDisable(false);
                cbModel.getItems().setAll(VehicleMake.vehicleMap.get(value).split(", "));
                cbModel.getItems().add("Other");
                customerModel.getVehicle().setMake(newValue);
            }
        });

        cbModel.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equalsIgnoreCase("Other")) {
                customerModel.getVehicle().setModel(newValue);
            }
        });

        txtMake.visibleProperty().bind(Bindings.equal(cbMake.valueProperty(), "Other"));
        txtModel.visibleProperty().bind(Bindings
                .equal(cbMake.valueProperty(), "Other")
                .or(Bindings.equal(cbModel.valueProperty(), "Other")));

        cbType.getItems().setAll(OilType.stringValues());
        cbType.valueProperty().addListener((observable, oldValue, newValue) -> {
            OilType value = OilType.fromString(newValue);
            cbKind.getItems().setAll(OilType.oilMap.get(value).split(", "));
        });

        cbKind.valueProperty().addListener((observable, oldValue, newValue) -> {
            String typeName = cbType.getValue() + " " + newValue;
            customerModel.getOil().setOilType(typeName);
        });

        bind();

        btnOk.setOnAction(e -> {
            boolean digitsValid = true;
            boolean lettersValid = true;
            boolean costValid = true;

            if (!Validations.digits(txtYear.getText()).repeat(4)) {
                lblDigitError.setText(lblDigitError.getText() + "the year (4 digits)");
                lblDigitError.setVisible(true);
                digitsValid = false;
            }

            if (Validations.digits(txtOilBrand.getText()).any()) {
                lblLetterError.setText(lblLetterError.getText() + "the oil brand");
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
                Task<Void> update = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        database.update(customerModel.getCustomer());
                        return null;
                    }
                };
                update.setOnSucceeded(v -> tableUpdateService.restart());
                update.exceptionProperty().addListener((observable, oldValue, newValue) -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Could not add value to database");
                    alert.show();
                    newValue.printStackTrace();
                });

                threadPool.execute(update);
                ((Stage)btnCancel.getScene().getWindow()).close();
            }
        });

        btnCancel.setOnAction(e -> ((Stage)btnCancel.getScene().getWindow()).close());
    }

    public void setCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
        String type = customerModel.getOil().getOilType().split("\\s\\d")[0];
        String kind = customerModel.getOil().getOilType().substring(
                customerModel.getOil().getOilType().lastIndexOf(' '));
        cbType.setValue(type);
        cbKind.setValue(kind);
        bind();
        bindTotalCost();
    }

    public void setOilChangeModel(OilChangeModel oilChangeModel) {
        this.oilChangeModel = oilChangeModel;
        bindTotalCost();
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void setThreadPool(ExecutorService threadPool) {
        this.threadPool = threadPool;
    }

    public void setTableUpdateService(DatabaseValueService tableUpdateService) {
        this.tableUpdateService = tableUpdateService;
    }

    private void bind() {
        cbMake.setValue(customerModel.getMake());
        cbModel.setValue(customerModel.getModel());

        txtMake.textProperty().bindBidirectional(customerModel.makeProperty());
        txtModel.textProperty().bindBidirectional(customerModel.modelProperty());
        txtYear.textProperty().bindBidirectional(customerModel.yearProperty());

        dtDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            customerModel.getDate().setMonth(newValue.getMonthValue());
            customerModel.getDate().setDay(newValue.getDayOfMonth());
            customerModel.getDate().setYear(newValue.getYear());
        });

        NumberStringConverter numberStringConverter = new NumberStringConverter();
        BigDecimalStringConverter bigDecimalStringConverter = new BigDecimalStringConverter();

        txtOilBrand.textProperty().bindBidirectional(customerModel.oilBrandProperty());
        txtOilQuantity.textProperty().bindBidirectional(customerModel.quantityProperty(), numberStringConverter);
        txtOilPrice.textProperty().bindBidirectional(customerModel.priceProperty(), bigDecimalStringConverter);
        txtFilterBrand.textProperty().bindBidirectional(customerModel.filterBrandProperty());
        txtFilterCost.textProperty().bindBidirectional(customerModel.filterCostProperty(), bigDecimalStringConverter);
    }

    private void bindTotalCost() {
        DecimalFormat currencyFormat = new DecimalFormat("#0.00");
        lblCost.textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal total = customerModel.getPrice()
                            .multiply(BigDecimal.valueOf(customerModel.getQuantity()))
                            .add(customerModel.getFilterCost()).add(oilChangeModel.getTotal());
                    return "$" + currencyFormat.format(total);
                }, customerModel.quantityProperty(), customerModel.priceProperty(),
                customerModel.filterCostProperty(), oilChangeModel.laborHoursProperty()));
    }
}
