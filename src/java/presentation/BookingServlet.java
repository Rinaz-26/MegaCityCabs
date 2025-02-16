package presentation;

import business.Booking;
import dataaccess.BookingDAO;
import dataaccess.CarDAO;
import business.Car;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import util.DatabaseConnection;

@WebServlet("/createBooking")
public class BookingServlet extends HttpServlet {
    private BookingDAO bookingDAO;
    private CarDAO carDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.bookingDAO = new BookingDAO(DatabaseConnection.getConnection());
        this.carDAO = new CarDAO(DatabaseConnection.getConnection());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerName = request.getParameter("customerName");
        int carId = Integer.parseInt(request.getParameter("carId"));
        
        Booking booking = new Booking();
        booking.setCustomerName(customerName);
        booking.setCarId(carId);
        booking.setBookingDate(new java.sql.Timestamp(System.currentTimeMillis()));

        // Calculate the bill
        double totalBill = booking.calculateBill();
        booking.setTotalBill(totalBill);

        // Add booking to the database
        bookingDAO.addBooking(booking);

        // Redirect to the booking confirmation page
        response.sendRedirect("bookingConfirmation.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get available cars for booking
        List<Car> availableCars = carDAO.getAllCars();
        request.setAttribute("availableCars", availableCars);
        RequestDispatcher dispatcher = request.getRequestDispatcher("booking.jsp");
        dispatcher.forward(request, response);
    }
}
