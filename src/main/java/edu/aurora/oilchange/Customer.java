package edu.aurora.oilchange;

public class Customer {
	private Vehicle vehiclle;
	private Oil oil;
	private int idNum;

	public Customer() {

	}

	public Customer(Vehicle vehic, Oil oil, int idNum) {
		this.vehiclle = vehic;
		this.oil = oil;
		this.idNum = idNum;
	}

	public Vehicle getVehiclle() {
		return vehiclle;
	}

	public void setVehiclle(Vehicle vehiclle) {
		this.vehiclle = vehiclle;
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
		return "Customer [vehiclle=" + vehiclle + ", oil=" + oil + ", idNum=" + idNum + "]";
	}
}
