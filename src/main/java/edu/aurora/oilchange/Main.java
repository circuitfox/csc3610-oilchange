package edu.aurora.oilchange;

import edu.aurora.oilchange.ui.AppLauncher;

import javafx.application.Application;

/**
 * Entry point of the application, used to initialize the database connection
 * and run the javafx app.
 */
public class Main {
    // TODO: Add DB
    public static void main(String[] args) {
        Application.launch(AppLauncher.class, args);
    }
}
