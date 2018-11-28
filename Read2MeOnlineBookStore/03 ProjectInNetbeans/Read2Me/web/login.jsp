<%-- 
    Document   : login
    Created on : Nov 15, 2018, 11:01:35 PM
    Author     : Dew2018
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://bootswatch.com/4/default/bootstrap.min.css">
        <script src="https://bootswatch.com/_vendor/jquery/dist/jquery.min.js"></script>
        <script src="https://bootswatch.com/_vendor/popper.js/dist/umd/popper.min.js"></script>
        <script src="https://bootswatch.com/_vendor/bootstrap/dist/js/bootstrap.min.js"></script>
    </head>
    <body>
        <form action="Login" method="post">
            <input type="hidden" name="returnUrl" value="${returnUrl}">
            <div class="container">
                <table class="table">
                    <tr>
                        <td>Email:</td>
                        <td><input type="text" name="email" required/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" required/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><p style="color:red">${message}</td>
                    </tr>
                    <p>${errorMassage}</p>
                </table>
                    <input type="submit" value="Login">
            </div>
        </form>
    </body>
</html>
