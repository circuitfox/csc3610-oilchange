package edu.aurora.oilchange;

import javafx.application.Application;

import java.util.HashMap;

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
		Application.launch(AppLauncher.class, args);
		vehicles = new HashMap<Integer, Vehicle>();

	}
}
