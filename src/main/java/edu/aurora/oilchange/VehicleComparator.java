package edu.aurora.oilchange;

import java.util.Comparator;

public class VehicleComparator<T extends Vehicle> implements Comparator<T> {
    @Override
    public int compare(Vehicle vehicle1, Vehicle vehicle2) {
        return vehicle1.compareTo(vehicle2);
    }
}
