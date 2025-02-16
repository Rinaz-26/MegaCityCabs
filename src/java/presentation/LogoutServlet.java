package presentation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for handling user logout.
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    // Handles the HTTP GET method to log the user out
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the current session to log the user out
        request.getSession().invalidate();

        // Redirect the user to the login page
        response.sendRedirect("login.jsp");
    }

    // Optionally, you can also handle POST requests the same way if required
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session and redirect to login page (same as doGet)
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "LogoutServlet - Invalidates the session and redirects to login page.";
    }
}
