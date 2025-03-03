<%@ page import="java.util.List" %>
<%@ page import="business.Car" %>
<%@ page import="dataaccess.CarDAO" %>
<%@ page import="util.DatabaseConnection" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.SQLException" %>
<!DOCTYPE html>
<html>
<head>
    <title>Car Management</title>
    <link rel="stylesheet" href="carManagement.css">
</head>
<body>
    <h1>Manage Cars</h1>

    <!-- Add/Edit Car Form -->
    <h2>Add/Edit Car</h2>
    <form action="CarManagementServlet1" method="post">
        <input type="hidden" name="action" id="formAction" value="add">
        <input type="hidden" name="car_Id" id="car_Id" value="">
        
        <label for="car_model">Car Model:</label>
        <input type="text" id="car_model" name="car_model" required><br><br>

        <label for="license_plate">License Plate:</label>
        <input type="text" id="license_plate" name="license_plate" required><br><br>

        <label for="status">Status:</label>
        <select id="status" name="status">
            <option value="Available">Available</option>
            <option value="Not Available">Not Available</option>
        </select><br><br>

        <input type="submit" value="OK">
    </form>

    <br><br>
    <!-- Display existing cars -->
    <h2>Existing Cars</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Car ID</th>
                <th>Car Model</th>
                <th>License Plate</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                Connection conn = null;
                try {
                    conn = DatabaseConnection.getConnection();
                    CarDAO carDAO = new CarDAO(conn);
                    List<Car> cars = carDAO.getAllCars();
                    for (Car car : cars) {
            %>
            <tr>
                <td><%= car.getCarId() %></td>
                <td><%= car.getCarModel() %></td>
                <td><%= car.getLicensePlate() %></td>
                <td><%= car.getStatus() %></td>
                <td>
                    <a href="#" onclick="editCar(<%= car.getCarId() %>, '<%= car.getCarModel() %>', '<%= car.getLicensePlate() %>', '<%= car.getStatus() %>')">Edit</a>
                    <a href="#" onclick="deleteCar(<%= car.getCarId() %>)">Delete</a>
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
        function editCar(car_Id, car_Model, license_Plate, status) {
            document.getElementById("formAction").value = "edit";
            document.getElementById("car_Id").value = car_Id;
            document.getElementById("car_model").value = car_Model;
            document.getElementById("license_plate").value = license_Plate;
            document.getElementById("status").value = status;
        }

        function deleteCar(carId) {
            if (confirm("Are you sure you want to delete this car?")) {
                window.location.href = "CarManagementServlet1?action=delete&car_Id=" + carId;
            }
        }
    </script>
</body>
</html>