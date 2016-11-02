package edu.aurora.oilchange.ui;

import edu.aurora.oilchange.OilChange;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.math.BigDecimal;

public class OilChangeModel {
    private OilChange oilChange;

    private DoubleProperty laborHours;

    public OilChangeModel() {
        this(new OilChange());
    }

    public OilChangeModel(OilChange oilChange) {
        this.oilChange = oilChange;

        this.laborHours = new SimpleDoubleProperty(this, "laborHours", 0);

        laborHours.addListener((observable, oldValue, newValue) -> this.oilChange.setLaborHours(newValue.doubleValue()));
    }

    public BigDecimal getTotal() {
        return oilChange.getTotal();
    }

    public double getLaborHours() {
        return laborHours.get();
    }

    public DoubleProperty laborHoursProperty() {
        return laborHours;
    }

    public void setLaborHours(double laborHours) {
        this.laborHours.set(laborHours);
    }

    public OilChange getOilChange() {
        return oilChange;
    }

    public void setOilChange(OilChange oilChange) {
        this.oilChange = oilChange;

        laborHours.set(oilChange.getLaborHours());
    }

    @Override
    public String toString() {
        return "Total Labor Cost: $" + oilChange.getTotal() + " (" +
                oilChange.getLaborHours() + " hours at $" +
                OilChange.PER_HOUR + "per hour, " +
                OilChange.TAX * 100 + "% tax";
    }
}
