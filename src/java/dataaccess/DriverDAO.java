package dataaccess;

import business.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDAO {
    private final Connection connection;

    public DriverDAO(Connection connection) {
        this.connection = connection;
    }

   
    public void addDriver(Driver driver) throws SQLException {
        String query = "INSERT INTO drivers (name, license_Number, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getDriverName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getStatus());
            stmt.executeUpdate();
        }
    }

   
    public List<Driver> getAllDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt("driver_Id"));
                driver.setDriverName(rs.getString("name"));
                driver.setLicenseNumber(rs.getString("license_Number"));
                driver.setStatus(rs.getString("status"));
                drivers.add(driver);
            }
        }
        return drivers;
    }

    
    public void updateDriver(Driver driver) throws SQLException {
        String query = "UPDATE drivers SET name = ?, license_Number = ?, status = ? WHERE driver_Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getDriverName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getStatus());
            stmt.setInt(4, driver.getDriverId());
            stmt.executeUpdate();
        }
    }

    
    public void deleteDriver(int driverId) throws SQLException {
        String query = "DELETE FROM drivers WHERE driver_Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverId);
            stmt.executeUpdate();
        }
    }
}