package edu.aurora.oilchange;

public class Customer {;
    private int id;
    private Vehicle vehicle;
    private Oil oil;
    private Date date;

    public Customer() {
        this.id = 0;
        this.vehicle = new Vehicle();
        this.oil = new Oil();
        this.date = new Date();
    }

    public Customer(int id, Vehicle vehicle, Oil oil, Date date) {
        this.id = id;
        this.vehicle = vehicle;
        this.oil = oil;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer [vehicle=" + vehicle + ", oil=" + oil + ", idNum=" + id + "]";
    }
}
