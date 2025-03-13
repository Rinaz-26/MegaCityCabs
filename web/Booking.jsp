<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Booking Form</title>
    <link rel="stylesheet" href="booking.css">
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
    
    <div class="form-container">
        <h2>Book a Trip</h2>
        <form action="BookingServlet" method="POST">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required><br/><br/>
            
            <label for="phone_number">Phone Number:</label>
            <input type="text" id="phone_number" name="phone_number" required><br/><br/>

            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required><br/><br/>

            <label for="pickup_location">Pickup Location:</label>
            <input type="text" id="pickup_location" name="pickup_location" required><br/><br/>

            <label for="drop_location">Drop Location:</label>
            <input type="text" id="drop_location" name="drop_location" required><br/><br/>

            <label for="distance">Distance (km):</label>
            <input type="number" id="distance" name="distance" required><br/><br/>

            <label for="date">Date:</label>
            <input type="date" id="date" name="date" required><br/><br/>

            <label for="car_id">Select Car:</label>
            <select id="car_id" name="car_id" required>
                <c:forEach var="car" items="${cars}">
                    <option value="${car.carId}">${car.carModel} - ${car.licensePlate}</option>
                </c:forEach>
            </select><br/><br/>

            <label for="driver_id">Select Driver:</label>
            <select id="driver_id" name="driver_id" required>
                <c:forEach var="driver" items="${drivers}">
                    <option value="${driver.driverId}">${driver.driverName}</option>
                </c:forEach>
            </select><br/><br/>

            <input type="submit" value="Book Trip"/>
        </form>
    </div>
    
    
</body>
</html>