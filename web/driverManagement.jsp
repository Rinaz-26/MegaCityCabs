<%@ page import="java.util.List" %>
<%@ page import="business.Driver" %>
<%@ page import="dataaccess.DriverDAO" %>
<%@ page import="util.DatabaseConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <title>Driver Management</title>
    <link rel="stylesheet" href="carManagement.css">
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>
    <h1>Welcome to Mega City Cab Booking System</h1>

    <div class="menu">
        <a href="carManagement..jsp">Manage Cars</a>
        <a href="driverManagement.jsp">Manage Drivers</a>
        <a href="BookingServlet">Create Booking</a>
        <a href="BookingList.jsp">View Bookings</a>
        <a href="help.jsp">Help</a>
        <a href="logout">Logout</a>
    </div>
    <form action="driverManagementServlet" method="post">
        <input type="hidden" name="action" id="formAction" value="add">
        <input type="hidden" name="driver_Id" id="driver_Id" value="">
        
        <label for="name">Driver Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <label for="license_number">License Number:</label>
        <input type="text" id="license_number" name="license_number" required><br><br>

        <label for="status">Status:</label>
        <select id="status" name="status">
            <option value="Available">Available</option>
            <option value="On_Trip">On_Trip</option>
        </select><br><br>

        <input type="submit" value="OK">
    </form>

    <br><br>
    
    <h2>Existing Drivers</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Driver ID</th>
                <th>Driver Name</th>
                <th>License Number</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                Connection conn = null;
                try {
                    conn = DatabaseConnection.getConnection();
                    DriverDAO driverDAO = new DriverDAO(conn);
                    List<Driver> drivers = driverDAO.getAllDrivers();
                    for (Driver driver : drivers) {
            %>
            <tr>
                <td><%= driver.getDriverId() %></td>
                <td><%= driver.getDriverName() %></td>
                <td><%= driver.getLicenseNumber() %></td>
                <td><%= driver.getStatus() %></td>
                <td>
                    <a href="#" onclick="editDriver(<%= driver.getDriverId() %>, '<%= driver.getDriverName() %>', '<%= driver.getLicenseNumber() %>', '<%= driver.getStatus() %>')">Edit</a>
                    <a href="#" onclick="deleteDriver(<%= driver.getDriverId() %>)">Delete</a>
                </td>
            </tr>
            <% 
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            %>
        </tbody>
    </table>

    <script>
        function editDriver(driver_Id, name, license_number, status) {
            document.getElementById("formAction").value = "edit";
            document.getElementById("driver_Id").value = driver_Id;
            document.getElementById("name").value = name;
            document.getElementById("license_number").value = license_number;
            document.getElementById("status").value = status;
        }

        function deleteDriver(driverId) {
            if (confirm("Are you sure you want to delete this driver?")) {
                window.location.href = "driverManagementServlet?action=delete&driver_Id=" + driverId;
            }
        }
    </script>
</body>
</html>