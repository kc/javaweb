<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <h2>Hello, please log in:</h2>
        <br><br>
        <form action="j_security_check" method="post">
            <p>User Name:<input type="text" name="j_username" size="25"></p>
            <p>Password: <input type="password" size="15" name="j_password"></p>
            <p><input type="submit" value="Login"></p>
            <p><input type="reset" value="Reset"></p>
        </form>
    </body>
</html>
