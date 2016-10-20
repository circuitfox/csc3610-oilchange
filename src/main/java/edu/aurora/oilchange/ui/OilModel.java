package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.Oil;

import javafx.beans.property.*;

import java.math.BigDecimal;

public class OilModel {
    private Oil oil;

    private StringProperty oilType;
    private StringProperty oilBrand;
    private IntegerProperty quantity;
    private ObjectProperty<BigDecimal> pricePerQuart;
    private StringProperty filterBrand;
    private ObjectProperty<BigDecimal> filterCost;

    public OilModel() {
        this.oil = new Oil();

        this.oilType = new SimpleStringProperty(this, "oilType", "");
        this.oilBrand = new SimpleStringProperty(this, "oilBrand", "");
        this.quantity = new SimpleIntegerProperty(this, "quantity", 0);
        this.pricePerQuart = new SimpleObjectProperty<>(this, "pricePerQuart", BigDecimal.ZERO);
        this.filterBrand = new SimpleStringProperty(this, "filterBrand", "");
        this.filterCost = new SimpleObjectProperty<>(this, "filterCost", BigDecimal.ZERO);

        oilType.addListener((observable, oldValue, newValue) -> oil.setOilType(newValue));
        oilBrand.addListener((observable, oldValue, newValue) -> oil.setOilBrand(newValue));
        quantity.addListener((observable, oldValue, newValue) -> oil.setQuantity(newValue.intValue()));
        pricePerQuart.addListener((observable, oldValue, newValue) -> oil.setPricePerQuart(newValue));
        filterBrand.addListener((observable, oldValue, newValue) -> oil.setFilterBrand(newValue));
        filterCost.addListener((observable, oldValue, newValue) -> oil.setFilterCost(newValue));
    }

    public String getOilType() {
        return oilType.get();
    }

    public StringProperty oilTypeProperty() {
        return oilType;
    }

    public void setOilType(String oilType) {
        this.oilType.set(oilType);
    }

    public String getOilBrand() {
        return oilBrand.get();
    }

    public StringProperty oilBrandProperty() {
        return oilBrand;
    }

    public void setOilBrand(String oilBrand) {
        this.oilBrand.set(oilBrand);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public BigDecimal getPricePerQuart() {
        return pricePerQuart.get();
    }

    public ObjectProperty<BigDecimal> pricePerQuartProperty() {
        return pricePerQuart;
    }

    public void setPricePerQuart(BigDecimal pricePerQuart) {
        this.pricePerQuart.set(pricePerQuart);
    }

    public String getFilterBrand() {
        return filterBrand.get();
    }

    public StringProperty filterBrandProperty() {
        return filterBrand;
    }

    public void setFilterBrand(String filterBrand) {
        this.filterBrand.set(filterBrand);
    }

    public BigDecimal getFilterCost() {
        return filterCost.get();
    }

    public ObjectProperty<BigDecimal> filterCostProperty() {
        return filterCost;
    }

    public void setFilterCost(BigDecimal filterCost) {
        this.filterCost.set(filterCost);
    }

    public Oil getOil() {
        return oil;
    }

    public void setOil(Oil oil) {
        this.oil = oil;

        oilType.set(oil.getOilType());
        oilBrand.set(oil.getOilBrand());
        quantity.set(oil.getQuantity());
        pricePerQuart.set(oil.getPricePerQuart());
        filterBrand.set(oil.getFilterBrand());
        filterCost.set(oil.getFilterCost());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Oil:\n");
        sb.append("\tOil Type: ").append(oilType.get()).append("\n");
        sb.append("\tOil Brand: ").append(oilBrand.get()).append("\n");
        sb.append("\tQuantity: ").append(quantity.get()).append("qt\n");
        sb.append("\tPrice: $").append(pricePerQuart.get()).append("/qt\n");
        sb.append("\tFilter Brand: ").append(filterBrand.get()).append("\n");
        sb.append("\tFilter Cost: $").append(filterCost.get()).append("\n");
        return sb.toString();
    }
}
