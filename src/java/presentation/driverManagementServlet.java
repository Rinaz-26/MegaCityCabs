/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentation;

import business.Driver;
import dataaccess.DriverDAO;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rinaz
 */
@WebServlet("/driverManagementServlet")
public class driverManagementServlet extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
         System.out.println("Action: " + action); 
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            DriverDAO driverDAO = new DriverDAO(conn);

            if (action != null) {
                switch (action) {
                    case "add" -> {
                        String Name = request.getParameter("name");
                        String licenseNumber = request.getParameter("license_number");
                        String status = request.getParameter("status");
                        Driver driver = new Driver();
                        driver.setDriverName(Name);
                        driver.setLicenseNumber(licenseNumber);
                        driver.setStatus(status);
                        driverDAO.addDriver(driver);
                    }
                    case "edit" -> {
                        int driverId = Integer.parseInt(request.getParameter("driver_Id"));
                        String name = request.getParameter("name");
                        String licenseNumber = request.getParameter("license_plate");
                        String status = request.getParameter("status");
                        Driver driver = new Driver();
                        driver.setDriverId(driverId);
                        driver.setDriverName(name);
                        driver.setLicenseNumber(licenseNumber);
                        driver.setStatus(status);
                        driverDAO.updateDriver(driver);
                         break;
                    }
                    default -> {
                         response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
                        return;
                    }
                }
            }

            response.sendRedirect("driverManagement.jsp");
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
            int driverId;
            try {
                driverId = Integer.parseInt(request.getParameter("driver_Id"));
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid driver ID format.");
                return;
            }

            Connection conn = null;
            try {
                conn = DatabaseConnection.getConnection();
                DriverDAO driverDAO = new DriverDAO(conn);
                driverDAO.deleteDriver(driverId);
                response.sendRedirect("driverManagement.jsp"); 
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
