package presentation;

import business.Booking;
import business.Car;
import business.Driver;
import dataaccess.BookingDAO;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.List;

public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

       
        if (action != null && action.equals("delete")) {
            int bookingId = Integer.parseInt(request.getParameter("id"));
            try {
                BookingDAO.deleteBooking(bookingId);  
                response.sendRedirect("BookingServlet");  
            } catch (SQLException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting booking.");
            }
            return;  
        }

        try {
          
            List<Car> cars = BookingDAO.getAvailableCars();
            List<Driver> drivers = BookingDAO.getAvailableDrivers();
            
           
            if (cars == null || drivers == null) {
                System.out.println("Cars or Drivers list is empty or null.");
            }

            
            request.setAttribute("cars", cars);
            request.setAttribute("drivers", drivers);

            
            RequestDispatcher dispatcher = request.getRequestDispatcher("Booking.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving cars/drivers.");
        }

        
        try {
            List<Booking> bookings = BookingDAO.getAllBookings();
            request.setAttribute("bookings", bookings);  
            RequestDispatcher dispatcher = request.getRequestDispatcher("BookingList.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving bookings.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phone_number");
        String email = request.getParameter("email");
        String pickupLocation = request.getParameter("pickup_location");
        String dropLocation = request.getParameter("drop_location");
        float distance = Float.parseFloat(request.getParameter("distance"));
        String date = request.getParameter("date");

        
        float fare = distance * 10;

        int carId = Integer.parseInt(request.getParameter("car_id"));
        int driverId = Integer.parseInt(request.getParameter("driver_id"));

        
        Booking booking = new Booking();
        booking.setName(name);
        booking.setPhoneNumber(phoneNumber);
        booking.setEmail(email);
        booking.setPickupLocation(pickupLocation);
        booking.setDropLocation(dropLocation);
        booking.setDistance(distance);
        booking.setDate(date);
        booking.setFare(fare);
        booking.setCarId(carId);
        booking.setDriverId(driverId);

        try {
            
            BookingDAO.saveBooking(booking);
            
            response.sendRedirect("BookingConfirmation.jsp");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving booking");
        }
    }
}