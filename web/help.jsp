<!DOCTYPE html>
<html>
<head>
    
    <title>Help Section</title>
    <link rel="stylesheet" href="dashboard.css">
    
     <style>
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

       
        h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #4CAF50;
        }

        
        .help-content {
            background-color: white;
            padding: 20px;
            max-width: 800px;
            width: 100%;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        
        h2 {
            font-size: 1.5rem;
            color: #333;
            margin-top: 20px;
            margin-bottom: 10px;
        }

       
        p {
            font-size: 1rem;
            line-height: 1.6;
            margin-bottom: 15px;
        }

        
        ul {
            list-style-type: none;
            padding-left: 20px;
        }

       
        ul li {
            font-size: 1rem;
            margin-bottom: 10px;
            line-height: 1.6;
        }

        ul li strong {
            color: #4CAF50;
        }

        
        @media (max-width: 768px) {
            body {
                padding: 10px;
            }

            .help-content {
                width: 100%;
                padding: 15px;
            }

            h2 {
                font-size: 1.2rem;
            }

            ul li {
                font-size: 0.9rem;
            }
        }
    </style>
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
    
    <h1>Help Section</h1>

    <div class="help-content">
        <h2>Getting Started with Mega City Cab</h2>
        <p>Welcome to Mega City Cab! Follow the steps below to get started:</p>
        <ul>
            <li><strong>Login:</strong> Use your username and password to log into the system. If you don't have an account, click on the "Sign Up" button to create one.</li>
            <li><strong>Manage Cars:</strong> Once logged in, you can add new cars, update existing car details, or remove cars from your fleet. To add a new car, navigate to the "Cars" section and click "Add New Car".</li>
            <li><strong>Manage Drivers:</strong> In the "Drivers" section, you can add, update, or remove drivers. Click on "Add Driver" to register a new driver, or edit driver information as needed.</li>
            <li><strong>Create Booking:</strong> Customers can book a ride by selecting a Available car and driver, entering their destination, and providing contact details. After booking, Can manage the ride via the "ViewBookings" tab.</li>
        </ul>

        <h2>Common Issues and Troubleshooting</h2>
        <ul>
            <li><strong>Forgotten Password:</strong> If you forget your password, click the "Forgot Password" link on the login page to reset it. If you're still having trouble, please contact support at <a href="mailto:support@megacitycab.com">support@megacitycab.com</a>.</li>
            <li><strong>Account Login Issues:</strong> If you're unable to log in, ensure your username and password are correct. If problems persist, clear your browser's cache and try again. For further assistance, reach out to support.</li>
            <li><strong>Car Not Showing Up in Fleet:</strong> If a recently added car doesn't show in the fleet, refresh the page. If the issue continues, check for any data entry errors or contact support.</li>
            <li><strong>Driver Not Assigned to Ride:</strong> Ensure the driver is marked as available in the system. If they're still not assigned, check the driver's schedule or manually assign them to the ride.</li>
        </ul>

        <h2>Contact Support</h2>
        <p>If you need further assistance, feel free to reach out to our support team:</p>
        <ul>
            <li><strong>Email:</strong> <a href="mailto:support@megacitycab.com">support@megacitycab.com</a></li>
            <li><strong>Phone:</strong> +94 776 534 5555 (Available 24/7)</li>
            <li><strong>Live Chat:</strong> Available on our website for instant support.</li>
        </ul>
    </div>
</body>
</html>
