
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Registration | Read2Me</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>
        <br><br>
        <center>
        <form action = "Register" method = "post" accept-charset = "UTF-8">
            <label>Email</label>
            <input type = "email" name = "email" maxlength = "200" required>
            <span style = "color:red">${emailNotice}</span><br>

            <label>Password</label>
            <input type = "password" name = "password" minlength = "8" title = "Password must contain at least 8 characters." required><br>

            <label>Firstname</label>
            <input type = "text" name = "firstname" maxlength = "50" required><br>

            <label>Lastname</label>
            <input type = "text" name = "lastname" maxlength = "50" required><br>

            <label>Mobile Phone</label>
            <input type = "tel" name = "phone" pattern = "[0-9]{10}" title = "The mobile phone number must contain 10 digits and number 0-9." required><br>
            
            <a href = "Login">Already have an account?</a>
            
            <input type = "submit" value = "Register">
        </form>
            
        </center>

        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
