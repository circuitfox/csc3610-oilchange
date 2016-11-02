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
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Could not load JDBC driver");
            ex.printStackTrace();
            System.exit(1);
        }
        Application.launch(AppLauncher.class, args);
    }
}
