package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.ui.DateModel;
import edu.aurora.oilchange.ui.OilChangeModel;
import edu.aurora.oilchange.ui.OilModel;
import edu.aurora.oilchange.ui.VehicleModel;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AddSummaryController {
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
    private Label lblTotalCost;

    private VehicleModel vehicleModel;
    private OilModel oilModel;
    private OilChangeModel oilChangeModel;
    private DateModel dateModel;

    public AddSummaryController() {
        vehicleModel = new VehicleModel();
        oilModel = new OilModel();
        oilChangeModel = new OilChangeModel();
        dateModel = new DateModel();
    }

    @FXML
    private void initialize() {
        DecimalFormat currencyFormat = new DecimalFormat("#0.00");

        lblMake.textProperty().bind(vehicleModel.makeProperty());
        lblModel.textProperty().bind(vehicleModel.modelProperty());
        lblYear.textProperty().bind(vehicleModel.yearProperty());

        lblDate.textProperty().bind(Bindings.createStringBinding(dateModel::toString,
                dateModel.monthProperty(), dateModel.dayProperty(), dateModel.yearProperty()));

        lblOilType.textProperty().bind(oilModel.oilTypeProperty());
        lblOilBrand.textProperty().bind(oilModel.oilBrandProperty());
        lblOilAmount.textProperty().bind(Bindings.createStringBinding(() -> oilModel.getQuantity() + "qt",
                oilModel.quantityProperty()));
        lblOilCost.textProperty().bind(Bindings.createStringBinding(() ->
                        "$" + currencyFormat.format(oilModel.getPricePerQuart()) + "/qt",
                oilModel.pricePerQuartProperty()));
        lblFilterBrand.textProperty().bind(oilModel.filterBrandProperty());
        lblFilterCost.textProperty().bind(Bindings.createStringBinding(
                () -> "$" + currencyFormat.format(oilModel.getFilterCost()),
                oilModel.filterCostProperty()));

        lblTotalCost.textProperty().bind(Bindings.createStringBinding(() -> {
                    BigDecimal total = oilModel.getPricePerQuart()
                            .multiply(BigDecimal.valueOf(oilModel.getQuantity()))
                            .add(oilModel.getFilterCost()).add(oilChangeModel.getTotal());
                    return "$" + currencyFormat.format(total);
                }, oilModel.quantityProperty(), oilModel.pricePerQuartProperty(),
                oilModel.filterCostProperty(), oilChangeModel.laborHoursProperty()));
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
