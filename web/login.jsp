<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="login-form">
        <h2>Login</h2>

        <form action="login" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <div id="error-message" style="color: red; display: none;">
                Invalid username or password. Please try again.
            </div>
            
            <button type="submit">Login</button>
        </form>
    </div>

    <script>
        
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.has('error')) {
            document.getElementById('error-message').style.display = 'block'; 
        }
    </script>
</body>
</html>