package edu.aurora.oilchange;

import java.time.LocalDate;
import java.util.Date;

public class Vehicle {
	private String make;
	private String model;
	private String year;
	private LocalDate date;

	public Vehicle() {
		// default
	}

	public Vehicle(String make, String model, String year, LocalDate date) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.date = date;
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Vehicle [make=" + make + ", model=" + model + ", year=" + year + ", date=" + date.toString() + "]";
	}

}
