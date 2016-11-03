package edu.aurora.oilchange.db;

import edu.aurora.oilchange.Customer;
import edu.aurora.oilchange.Date;
import edu.aurora.oilchange.Oil;
import edu.aurora.oilchange.Vehicle;

import java.sql.*;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Database {
    private String url;
    private String username;
    private String password;

    public Database(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Database(Properties properties) {
        this(properties.getProperty("db.url"), properties.getProperty("db.user"), properties.getProperty("db.pass"));
    }

    public void insert(Vehicle vehicle, Oil oil, Date date) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.setAutoCommit(false);

            PreparedStatement insertVehicle =
                    connection.prepareStatement("insert into Vehicle values (null, ?, ?, ?);",
                            Statement.RETURN_GENERATED_KEYS);
            insertVehicle.setString(1, vehicle.getMake());
            insertVehicle.setString(2, vehicle.getModel());
            insertVehicle.setString(3, vehicle.getYear());

            try {
                insertVehicle.executeUpdate();
                ResultSet rs = insertVehicle.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);

                PreparedStatement insertOil =
                        connection.prepareStatement("insert into Oil values (?, ?, ?, ?, ?, ?, ?);");
                insertOil.setInt(1, id);
                insertOil.setString(2, oil.getOilType());
                insertOil.setString(3, oil.getOilBrand());
                insertOil.setInt(4, oil.getQuantity());
                insertOil.setBigDecimal(5, oil.getPricePerQuart());
                insertOil.setString(6, oil.getFilterBrand());
                insertOil.setBigDecimal(7, oil.getFilterCost());
                insertOil.executeUpdate();

                PreparedStatement insertDate =
                        connection.prepareStatement("insert into Date values (?, ?, ?, ?);");
                insertDate.setInt(1, id);
                insertDate.setInt(2, date.getMonth());
                insertDate.setInt(3, date.getDay());
                insertDate.setInt(4, date.getYear());
                insertDate.executeUpdate();

                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public void update(Customer customer) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.setAutoCommit(false);
            int id = customer.getId();
            Vehicle vehicle = customer.getVehicle();
            Oil oil = customer.getOil();
            Date date = customer.getDate();

            try {
                PreparedStatement updateStatement = connection.prepareStatement("update Vehicle, Oil, Date set " +
                        "Vehicle.make = ?, Vehicle.model = ?, Vehicle.year = ?, " +
                        "Oil.type = ?, Oil.brand = ?, Oil.quantity = ?, " +
                        "Oil.price = ?, Oil.filterBrand = ?, Oil.filterCost = ?, " +
                        "Date.month = ?, Date.day = ?, Date.year = ? " +
                        "where Vehicle.id = ? and Oil.id = ? and Date.id = ?;");
                updateStatement.setString(1, vehicle.getMake());
                updateStatement.setString(2, vehicle.getModel());
                updateStatement.setString(3, vehicle.getYear());
                updateStatement.setString(4, oil.getOilType());
                updateStatement.setString(5, oil.getOilBrand());
                updateStatement.setInt(6, oil.getQuantity());
                updateStatement.setBigDecimal(7, oil.getPricePerQuart());
                updateStatement.setString(8, oil.getFilterBrand());
                updateStatement.setBigDecimal(9, oil.getFilterCost());
                updateStatement.setInt(10, date.getMonth());
                updateStatement.setInt(11, date.getDay());
                updateStatement.setInt(12, date.getYear());
                updateStatement.setInt(13, id);
                updateStatement.setInt(14, id);
                updateStatement.setInt(15, id);
                updateStatement.executeUpdate();

                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public void delete(Customer customer) throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement deleteStatement = connection.prepareStatement("delete from Vehicle where id = ?;");
                deleteStatement.setInt(1, customer.getId());
                deleteStatement.executeUpdate();

                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public Set<Customer> selectAll() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement selectStatement =
                        connection.prepareStatement("select * from Vehicle " +
                                "inner join Oil on Oil.id = Vehicle.id " +
                                "inner join Date on Date.id = Vehicle.id;");
                ResultSet rs = selectStatement.executeQuery();
                Set<Customer> results = new HashSet<>();

                while (rs.next()) {
                    Vehicle vehicle = new Vehicle(rs.getString(2), rs.getString(3), rs.getString(4));
                    Oil oil = new Oil(rs.getString(6), rs.getString(7), rs.getInt(8),
                            rs.getBigDecimal(9), rs.getString(10), rs.getBigDecimal(11));
                    Date date = new Date(rs.getInt(13), rs.getInt(14), rs.getInt(15));
                    Customer customer = new Customer(rs.getInt(1), vehicle, oil, date);
                    results.add(customer);
                }

                connection.commit();
                return results;
            } catch (SQLException ex) {
                connection.rollback();
                throw ex;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Database: " + username + "@" + url;
    }
}
