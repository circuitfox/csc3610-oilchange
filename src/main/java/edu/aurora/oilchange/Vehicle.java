package edu.aurora.oilchange;

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
    public int compareTo(Vehicle vehicle) {
        if (this.getMake().compareTo(vehicle.getMake()) > 1) {
            return 1;
        } else if (this.getMake().compareTo(vehicle.getMake()) < 1)
            return -1;
        else if (this.getModel().compareTo(vehicle.getModel()) > 1) {
            return 1;
        } else if (this.getModel().compareTo(vehicle.getModel()) < 1)
            return -1;
        else if (this.getYear().compareTo(vehicle.getYear()) > 1) {
            return 1;
        } else if (this.getYear().compareTo(vehicle.getYear()) < 1)
            return -1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle)o;

        if (make != null ? !make.equals(vehicle.make) : vehicle.make != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        return year != null ? year.equals(vehicle.year) : vehicle.year == null;

    }

    @Override
    public int hashCode() {
        int result = make != null ? make.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
