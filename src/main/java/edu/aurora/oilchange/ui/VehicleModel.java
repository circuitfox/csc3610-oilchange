package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.Vehicle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VehicleModel {
    private Vehicle vehicle;

    private StringProperty make;
    private StringProperty model;
    private StringProperty year;

    public VehicleModel() {
        vehicle = new Vehicle();

        make = new SimpleStringProperty(this, "make", "");
        model = new SimpleStringProperty(this, "model", "");
        year = new SimpleStringProperty(this, "year", "");

        make.addListener((observable, oldValue, newValue) -> vehicle.setMake(newValue));
        model.addListener((observable, oldValue, newValue) -> vehicle.setModel(newValue));
        year.addListener((observable, oldValue, newValue) -> vehicle.setYear(newValue));
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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

        make.set(vehicle.getMake());
        model.set(vehicle.getModel());
        year.set(vehicle.getYear());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vehicle:\n");
        sb.append("\tMake: ").append(make.get()).append("\n");
        sb.append("\tModel: ").append(model.get()).append("\n");
        sb.append("\tYear: ").append(year.get()).append("\n");
        return sb.toString();
    }
}
