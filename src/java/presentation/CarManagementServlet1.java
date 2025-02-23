package presentation;

import business.Car;
import dataaccess.CarDAO;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CarManagementServlet1")
public class CarManagementServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            CarDAO carDAO = new CarDAO(conn);

            if (action != null) {
                switch (action) {
                    case "add" -> {
                        String carModel = request.getParameter("car_model");
                        String licensePlate = request.getParameter("license_plate");
                        String status = request.getParameter("status");
                        Car car = new Car();
                        car.setCarModel(carModel);
                        car.setLicensePlate(licensePlate);
                        car.setStatus(status);
                        carDAO.addCar(car);
                    }
                    case "edit" -> {
                        int carId = Integer.parseInt(request.getParameter("car_id"));
                        String carModel = request.getParameter("car_model");
                        String licensePlate = request.getParameter("license_plate");
                        String status = request.getParameter("status");
                        Car car = new Car();
                        car.setCarId(carId);
                        car.setCarModel(carModel);
                        car.setLicensePlate(licensePlate);
                        car.setStatus(status);
                        carDAO.updateCar(car);
                    }
                    default -> {
                    }
                }
            }

            response.sendRedirect("carManagement..jsp"); // Corrected the redirect URL
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int carId = Integer.parseInt(request.getParameter("car_id"));
            Connection conn = null;
            try {
                conn = DatabaseConnection.getConnection();
                CarDAO carDAO = new CarDAO(conn);
                carDAO.deleteCar(carId);
                response.sendRedirect("carManagement..jsp"); // Corrected the redirect URL
            } catch (SQLException e) {
                e.printStackTrace(); // Log the exception for debugging
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}