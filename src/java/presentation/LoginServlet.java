/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import business.User;
import dataaccess.UserDAO;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import util.DatabaseConnection;

/**
 *
 * @author Rinaz
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
        private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the connection to the database
        this.userDAO = new UserDAO(DatabaseConnection.getConnection());
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
        @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Retrieve the user from the database
        User user = userDAO.getUserByUsername(username);

        // Check if user exists and the password is correct
        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            // Start a new session and store the user object
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect to the dashboard page
            response.sendRedirect("dashboard.jsp");
        } else {
            // If login fails, redirect back to login page with an error message
            response.sendRedirect("login.jsp?error=true");
        }
    }
}