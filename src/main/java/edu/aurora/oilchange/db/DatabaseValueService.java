package edu.aurora.oilchange.db;

import edu.aurora.oilchange.Customer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.Set;

/**
 * Retrieves the values from a database on a separate thread.
 */
public class DatabaseValueService extends Service<Set<Customer>> {
    private ObjectProperty<Database> database = new SimpleObjectProperty<>(this, "database", null);

    @Override
    protected Task<Set<Customer>> createTask() {
        final Database db = database.get();
        return new Task<Set<Customer>>() {
            @Override
            protected Set<Customer> call() throws Exception {
                return db.selectAll();
            }
        };
    }

    public final Database getDatabase() {
        return database.get();
    }

    public final ObjectProperty<Database> databaseProperty() {
        return database;
    }

    public final void setDatabase(Database database) {
        this.database.set(database);
    }
}
