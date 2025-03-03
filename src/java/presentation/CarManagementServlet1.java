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
         System.out.println("Action: " + action); 
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
                        int carId = Integer.parseInt(request.getParameter("car_Id"));
                        String carModel = request.getParameter("car_model");
                        String licensePlate = request.getParameter("license_plate");
                        String status = request.getParameter("status");
                        Car car = new Car();
                        car.setCarId(carId);
                        car.setCarModel(carModel);
                        car.setLicensePlate(licensePlate);
                        car.setStatus(status);
                        carDAO.updateCar(car);
                         break;
                    }
                    default -> {
                         response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                        return;
                    }
                }
            }

            response.sendRedirect("carManagement..jsp");
        } catch (SQLException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int carId;
            try {
                carId = Integer.parseInt(request.getParameter("car_Id"));
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid car ID format.");
                return;
            }

            Connection conn = null;
            try {
                conn = DatabaseConnection.getConnection();
                CarDAO carDAO = new CarDAO(conn);
                carDAO.deleteCar(carId);
                response.sendRedirect("carManagement..jsp"); 
            } catch (SQLException e) { 
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }
}