
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Login | Read2Me</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>

        <form action = "Login" method = "post" accept-charset = "UTF-8">
            <input type="hidden" name="returnUrl" value="${returnUrl}">
            <label>Email</label>
            <input type = "email" name = "email" required/><br>

            <label>Password</label>
            <input type="password" name = "password" minlength = "8" required/>
            <span style = "color:red">${notice}</span><br>
            
            <a href = "Register">Don't have an account?</a>

            <input type = "submit" value = "Login"/>
        </form>

        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
