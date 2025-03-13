package dataaccess;

import business.Booking;
import business.Car;
import business.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;

public class BookingDAO {

    
    public static List<Car> getAvailableCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars WHERE status = 'Available'";
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Car car = new Car();
                car.setCarId(rs.getInt("car_id"));
                car.setCarModel(rs.getString("car_model"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setStatus(rs.getString("status"));
                cars.add(car);
            }
        }
        return cars;
    }

    
    public static List<Driver> getAvailableDrivers() throws SQLException {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers WHERE status = 'available'";
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setDriverId(rs.getInt("driver_id"));
                driver.setDriverName(rs.getString("name"));
                driver.setLicenseNumber(rs.getString("license_number"));
                driver.setStatus(rs.getString("status"));
                drivers.add(driver);
            }
        }
        return drivers;
    }

    
    public static int saveBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings (name, phone_number, email, pickup_location, drop_location, distance, date, fare, car_id, driver_id, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, booking.getName());
            stmt.setString(2, booking.getPhoneNumber());
            stmt.setString(3, booking.getEmail());
            stmt.setString(4, booking.getPickupLocation());
            stmt.setString(5, booking.getDropLocation());
            stmt.setFloat(6, booking.getDistance());
            stmt.setString(7, booking.getDate());
            stmt.setFloat(8, booking.getFare());
            stmt.setInt(9, booking.getCarId());
            stmt.setInt(10, booking.getDriverId());
            stmt.setString(11, "pending");
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }
    
    public static List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT booking_id, name, phone_number, email, pickup_location, drop_location, distance, date, fare, car_id, driver_id, status FROM bookings";
        
        try (Connection con = DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setName(rs.getString("name"));
                booking.setPhoneNumber(rs.getString("phone_number"));
                booking.setEmail(rs.getString("email"));
                booking.setPickupLocation(rs.getString("pickup_location"));
                booking.setDropLocation(rs.getString("drop_location"));
                booking.setDistance(rs.getFloat("distance"));
                booking.setDate(rs.getString("date"));
                booking.setFare(rs.getFloat("fare"));
                booking.setCarId(rs.getInt("car_id"));
                booking.setDriverId(rs.getInt("driver_id"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
        }
        return bookings;
    }

   
    public static void deleteBooking(int bookingId) throws SQLException {
        String query = "DELETE FROM bookings WHERE booking_id = ?";
        
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            stmt.executeUpdate();
        }
    }
}
