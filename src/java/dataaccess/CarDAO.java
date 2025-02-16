/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import business.Car;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Rinaz
 */
public class CarDAO {
    private Connection connection;

    public CarDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCar(Car car) {
        try {
            String sql = "INSERT INTO Cars (car_type, driver_id, available) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, car.getCarType());
            stmt.setInt(2, car.getDriverId());
            stmt.setBoolean(3, car.isAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Car> getAllCars() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
