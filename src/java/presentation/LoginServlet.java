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
        
       
        User user = userDAO.getUserByUsername(username);

        
        if (user != null && user.getPassword() != null && user.getPassword().equals(password)) {
            
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            
            response.sendRedirect("dashboard.jsp");
        } else {
           
            response.sendRedirect("login.jsp?error=true");
        }
    }
}