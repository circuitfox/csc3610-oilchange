package edu.aurora.oilchange;

import javafx.application.Application;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

import edu.aurora.oilchange.ui.AppLauncher;

/**
 * Entry point of the application, used to initialize the database connection
 * and run the javafx app.
 */
public class Main {
	// TODO: Replace all this with our DB
	public static Customer customer;
	public static HashMap vehicles;

	public static void main(String[] args) {
		vehicles = new HashMap<Integer, Vehicle>();
		Vehicle vehic1 = new Vehicle("Ford", "Focus", "1996");
		Vehicle vehic2 = new Vehicle("Honda", "Civic", "2012");
		SortedSet<Vehicle> vehicleTest = new TreeSet<Vehicle>(new VehicleComparator<Vehicle>());
		vehicleTest.add(vehic1);
		vehicleTest.add(vehic2);
		Date d1 = new Date(11, 4, 2016);
		Date d2 = new Date(11, 5, 2016);
		Oil o1 = new Oil();
		Oil o2 = new Oil();
		o1.setOilBrand("Castrol");
		o1.setOilType("Synthetic");
		o1.setPricePerQuart(new BigDecimal(12));
		o2.setOilBrand("Castrol");
		o2.setOilType("Synthetic");
		o2.setPricePerQuart(new BigDecimal(13));
		SortedSet<Date> dateTest = new TreeSet<Date>(new DateComparator<Date>());
		dateTest.add(d1);
		dateTest.add(d2);
		SortedSet<Oil> oilTest = new TreeSet<Oil>(new OilComparator<Oil>());
		oilTest.add(o1);
		oilTest.add(o2);
		for (Vehicle v : vehicleTest)
			System.out.println(v.toString());
		for (Date d : dateTest)
			System.out.println(d.toString());
		for (Oil o : oilTest)
			System.out.println(o.toString());
		Application.launch(AppLauncher.class, args);

	}
}
