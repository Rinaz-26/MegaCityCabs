<%@ page import="java.util.List" %>
<%@ page import="business.Booking" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Booking List</title>
    <link rel="stylesheet" href="bookingList.css">
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
    
    <div class="container">
        <h2>Booking List</h2>

       
        <table border="1">
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Name</th>
                    <th>Phone Number</th>
                    <th>Email</th>
                    <th>Pickup Location</th>
                    <th>Drop Location</th>
                    <th>Distance (km)</th>
                    <th>Date</th>
                    <th>Fare</th>
                    <th>Car ID</th>
                    <th>Driver ID</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${booking.bookingId}</td>
                        <td>${booking.name}</td>
                        <td>${booking.phoneNumber}</td>
                        <td>${booking.email}</td>
                        <td>${booking.pickupLocation}</td>
                        <td>${booking.dropLocation}</td>
                        <td>${booking.distance}</td>
                        <td>${booking.date}</td>
                        <td>${booking.fare}</td>
                        <td>${booking.carId}</td>
                        <td>${booking.driverId}</td>
                        <td>${booking.status}</td>
                        <td>
                            <a href="BookingServlet?action=delete&id=${booking.bookingId}" 
                               onclick="return confirm('Are you sure you want to cancel this booking?');">Cancel</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty bookings}">
                    <tr>
                        <td colspan="13">No bookings available</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
        <br/>
        <a href="BookingServlet">Create New Booking</a>
    </div>
</body>
</html>
