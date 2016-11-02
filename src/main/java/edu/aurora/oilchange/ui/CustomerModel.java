package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.Customer;
import edu.aurora.oilchange.Date;
import edu.aurora.oilchange.Oil;
import edu.aurora.oilchange.Vehicle;
import javafx.beans.property.*;

import java.math.BigDecimal;

public class CustomerModel {
    private Customer customer;

    private IntegerProperty id;

    private StringProperty make;
    private StringProperty model;
    private StringProperty year;

    private StringProperty oilType;
    private StringProperty oilBrand;
    private IntegerProperty quantity;
    private ObjectProperty<BigDecimal> price;
    private StringProperty filterBrand;
    private ObjectProperty<BigDecimal> filterCost;

    private ObjectProperty<Date> date;

    public CustomerModel() {
        this(new Customer());
    }

    public CustomerModel(Customer customer) {
        this.customer = customer;

        this.id = new SimpleIntegerProperty(this, "id", customer.getId());

        this.make = new SimpleStringProperty(this, "make", customer.getVehicle().getMake());
        this.model = new SimpleStringProperty(this, "model", customer.getVehicle().getModel());
        this.year = new SimpleStringProperty(this, "year", customer.getVehicle().getYear());

        this.oilType = new SimpleStringProperty(this, "oilType", customer.getOil().getOilType());
        this.oilBrand = new SimpleStringProperty(this, "oilBrand", customer.getOil().getOilBrand());
        this.quantity = new SimpleIntegerProperty(this, "quantity", customer.getOil().getQuantity());
        this.price = new SimpleObjectProperty<>(this, "price", customer.getOil().getPricePerQuart());
        this.filterBrand = new SimpleStringProperty(this, "filterBrand", customer.getOil().getFilterBrand());
        this.filterCost = new SimpleObjectProperty<>(this, "filterCost", customer.getOil().getFilterCost());
        this.date = new SimpleObjectProperty<>(this, "date", customer.getDate());

        id.addListener((observable, oldValue, newValue) -> this.customer.setId(newValue.intValue()));

        make.addListener((observable, oldValue, newValue) -> this.customer.getVehicle().setMake(newValue));
        model.addListener((observable, oldValue, newValue) -> this.customer.getVehicle().setModel(newValue));
        year.addListener((observable, oldValue, newValue) -> this.customer.getVehicle().setYear(newValue));

        oilType.addListener((observable, oldValue, newValue) -> this.customer.getOil().setOilType(newValue));
        oilBrand.addListener((observable, oldValue, newValue) -> this.customer.getOil().setOilBrand(newValue));
        quantity.addListener((observable, oldValue, newValue) ->
                this.customer.getOil().setQuantity(newValue.intValue()));
        price.addListener((observable, oldValue, newValue) -> this.customer.getOil().setPricePerQuart(newValue));
        filterBrand.addListener((observable, oldValue, newValue) -> this.customer.getOil().setFilterBrand(newValue));
        filterCost.addListener((observable, oldValue, newValue) -> this.customer.getOil().setFilterCost(newValue));

        date.addListener((observable, oldValue, newValue) -> this.customer.setDate(newValue));
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getMake() {
        return make.get();
    }

    public StringProperty makeProperty() {
        return make;
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
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

    public BigDecimal getPrice() {
        return price.get();
    }

    public ObjectProperty<BigDecimal> priceProperty() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
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

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    public void setDate(Date date) {
        this.date.set(date);
    }

    public Vehicle getVehicle() {
        return customer.getVehicle();
    }

    public Oil getOil() {
        return customer.getOil();
    }
}
