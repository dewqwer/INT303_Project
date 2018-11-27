<%-- 
    Document   : register
    Created on : Nov 1, 2018, 11:16:49 PM
    Author     : Dew2018
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="Register" method="post" accept-charset="UTF-8">
            <br>
            Email: <input type="email" name="email" required>
            <p style="color:red">${message}</p>
            <br>
            Password: <input type="password" name="password" required>
            <br>
            First name :<input type="text" name="firstName" required>
            <br>
            Last name :<input type="text" name="lastName" required>
            <br>
            Phone :<input type="tel" name="phone" required>
            <br>
           
            <input type="submit" value="Confirm">
        </form>
    </body>
</html>
