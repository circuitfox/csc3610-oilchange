package edu.aurora.oilchange;

public class Vehicle {

	private String make;
	private String model;
	private String year;
	
	
	
	public Vehicle(){
		//default
	}
	public Vehicle(String makeIn, String modelIn, String yearIn){
		
		this.make = makeIn;
		this.model = modelIn;
		this.year = yearIn;
		
		
	}
	
	public void setMake(String makeIn){
		this.make = makeIn;
	}
	public void setModel(String modelIn){
		this.model = modelIn;
	}
	public void setYear(String yearIn){
		this.year = yearIn;
	}
	public String getMake(){
		return this.make;
	}
	public String getModel(){
		return this.model;
	}
	public String getYear(){
		return this.year;
	}
	@Override
	public String toString(){
		return this.getMake() + ", " + this.getModel() + ", " + this.getYear();
	}
}
