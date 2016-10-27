package edu.aurora.oilchange;

import java.time.LocalDate;

// TODO: Remove date
public class Vehicle implements Comparable<Vehicle> {
	private String make;
	private String model;
	private String year;

	public Vehicle() {
		// default
	}

	public Vehicle(String make, String model, String year) {
		this.make = make;
		this.model = model;
		this.year = year;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMake() {
		return this.make;
	}

	public String getModel() {
		return this.model;
	}

	public String getYear() {
		return this.year;
	}

	@Override
	public String toString() {
		return "Vehicle [make=" + make + ", model=" + model + ", year=" + year + "]";
	}

	// returns vehicles in ascending order starting with the make, then model, then year
	@Override
	public int compareTo(Vehicle vehic) {
		if (this.getMake().compareTo(vehic.getMake()) > 1) {
			return 1;
		} else if (this.getMake().compareTo(vehic.getMake()) < 1)
			return -1;
		else if (this.getModel().compareTo(vehic.getModel()) > 1) {
			return 1;
		} else if (this.getModel().compareTo(vehic.getModel()) < 1)
			return -1;
		else if (this.getYear().compareTo(vehic.getYear()) > 1) {
			return 1;
		} else if (this.getYear().compareTo(vehic.getYear()) < 1)
			return -1;
		else
			return 0;
	}

}
