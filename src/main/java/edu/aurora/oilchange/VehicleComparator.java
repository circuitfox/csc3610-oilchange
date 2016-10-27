package edu.aurora.oilchange;

import java.util.Comparator;

public class VehicleComparator<T extends Vehicle> implements Comparator<T> {

	@Override
	public int compare(Vehicle vehic1, Vehicle vehic2) {
		return vehic1.compareTo(vehic2);
	}

}
