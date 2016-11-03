package edu.aurora.oilchange;

public class Customer {
    public static int OILCHANGE_DURATION_MONTHS = 3;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer)o;

        if (id != customer.id) return false;
        if (vehicle != null ? !vehicle.equals(customer.vehicle) : customer.vehicle != null) return false;
        if (oil != null ? !oil.equals(customer.oil) : customer.oil != null) return false;
        return date != null ? date.equals(customer.date) : customer.date == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vehicle != null ? vehicle.hashCode() : 0);
        result = 31 * result + (oil != null ? oil.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
