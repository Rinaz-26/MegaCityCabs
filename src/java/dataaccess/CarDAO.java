package dataaccess;

import business.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    private final Connection connection;

    public CarDAO(Connection connection) {
        this.connection = connection;
    }

    // Add new car
    public void addCar(Car car) throws SQLException {
        String query = "INSERT INTO cars (car_Model, license_Plate, status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, car.getCarModel());
            stmt.setString(2, car.getLicensePlate());
            stmt.setString(3, car.getStatus());
            stmt.executeUpdate();
        }
    }

    // Get all cars
    public List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_Id"));
                car.setCarModel(rs.getString("car_Model"));
                car.setLicensePlate(rs.getString("license_Plate"));
                car.setStatus(rs.getString("status"));
                cars.add(car);
            }
        }
        return cars;
    }

    // Update car
    public void updateCar(Car car) throws SQLException {
        String query = "UPDATE cars SET car_Model = ?, license_Plate = ?, status = ? WHERE car_Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, car.getCarModel());
            stmt.setString(2, car.getLicensePlate());
            stmt.setString(3, car.getStatus());
            stmt.setInt(4, car.getCarId());
            stmt.executeUpdate();
        }
    }

    // Delete car
    public void deleteCar(int carId) throws SQLException {
        String query = "DELETE FROM cars WHERE car_Id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carId);
            stmt.executeUpdate();
        }
    }
}