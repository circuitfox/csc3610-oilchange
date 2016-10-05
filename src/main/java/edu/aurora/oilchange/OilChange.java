package edu.aurora.oilchange;

public class OilChange {
	
	private static final int PER_HOUR = 15;//&15 an hour
	private static final double TAX = .07;//7%
	private double laborHours;
	
	
	
	public OilChange() {
		
	}
	
	public OilChange(double laborHours) {
		this.laborHours = laborHours;
	}
	
	public static int getPerHour() {
		return PER_HOUR;
	}

	public static double getTax() {
		return TAX;
	}
	
	public double getLaborHours() {
		return this.laborHours;
	}
	
	public double getTotal() {
		return (this.laborHours*PER_HOUR)+((this.laborHours*PER_HOUR)*TAX);
	}
	
	
	
	
}
