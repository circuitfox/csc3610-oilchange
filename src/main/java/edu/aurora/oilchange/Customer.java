package edu.aurora.oilchange;

public class Customer {
	private Vehicle vehicle;
	private Oil oil;
	private int idNum;

	public Customer() {

	}

	public Customer(Vehicle vehicle, Oil oil, int idNum) {
		this.vehicle = vehicle;
		this.oil = oil;
		this.idNum = idNum;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Oil getOil() {
		return oil;
	}

	public void setOil(Oil oil) {
		this.oil = oil;
	}

	public int getIdNum() {
		return idNum;
	}

	public void setIdNum(int idNum) {
		this.idNum = idNum;
	}

	@Override
	public String toString() {
		return "Customer [vehicle=" + vehicle + ", oil=" + oil + ", idNum=" + idNum + "]";
	}
}
