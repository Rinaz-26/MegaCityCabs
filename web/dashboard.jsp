<%@page import="com.sun.jdi.connect.spi.Connection"%>
<%@page import="util.DatabaseConnection"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="dashboard.css">
</head>
<body>
    <h1>Welcome to Mega City Cab Booking System</h1>

    <div class="menu">
        <a href="carManagement..jsp">Manage Cars</a>
        <a href="driverManagement.jsp">Manage Drivers</a>
        <a href="booking.jsp">Create Booking</a>
        <a href="help.jsp">Help</a>
        <a href="logout">Logout</a>
    </div>

    <div class="content">
        <h2>System Overview</h2>
        <p>This is your dashboard, where you can manage all aspects of the cab service.</p
        
    </div>
</body>
</html>
