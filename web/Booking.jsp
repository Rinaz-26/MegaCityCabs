<!DOCTYPE html>
<html>
<head>
    <title>Booking</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <form action="createBooking" method="post">
        <label for="customerName">Customer Name:</label>
        <input type="text" id="customerName" name="customerName" required>
        <label for="carId">Car ID:</label>
        <input type="number" id="carId" name="carId" required>
        <button type="submit">Create Booking</button>
    </form>
</body>
</html>
