/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccess;

import business.Booking;
import java.sql.*;
/**
 *
 * @author Rinaz
 */
public class BookingDAO {
    private final Connection connection;

    public BookingDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBooking(Booking booking) {
        try {
            String sql = "INSERT INTO Bookings (customer_name, car_id, booking_date, total_bill) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, booking.getCustomerName());
            stmt.setInt(2, booking.getCarId());
            stmt.setTimestamp(3, booking.getBookingDate());
            stmt.setDouble(4, booking.calculateBill());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
