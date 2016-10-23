package edu.aurora.oilchange;

import java.math.BigDecimal;

public class OilChange {
    public static final int PER_HOUR = 15; // $15 an hour
    public static final double TAX = .07; // 7%

    private double laborHours;

    public OilChange() {
        this.laborHours = 1;
    }

    public OilChange(double laborHours) {
        this.laborHours = laborHours;
    }

    public double getLaborHours() {
        return this.laborHours;
    }

    public void setLaborHours(double laborHours) {
        this.laborHours = laborHours;
    }

    public BigDecimal getTotal() {
        return BigDecimal.valueOf(this.laborHours)
                .multiply(BigDecimal.valueOf(PER_HOUR))
                .multiply(BigDecimal.valueOf(1 + TAX));
    }

    @Override
    public String toString() {
        return "OilChange{" + "laborHours=" + laborHours + '}';
    }
}
