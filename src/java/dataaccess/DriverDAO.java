package dataaccess;

import business.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    private Connection connection;

    public DriverDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to add a new driver
    public void addDriver(Driver driver) {
        try {
            String sql = "INSERT INTO Drivers (name, phone) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getPhone());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get a list of all drivers
    public List<Driver> getAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Drivers";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt("driver_id"));
                driver.setName(rs.getString("name"));
                driver.setPhone(rs.getString("phone"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
}