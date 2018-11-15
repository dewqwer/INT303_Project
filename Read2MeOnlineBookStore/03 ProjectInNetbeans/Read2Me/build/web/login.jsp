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
    </head>
    <body>
        <div class="container">
            <form action="Login" method="post">
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
                <tr>
                    <td><input type="submit" name="Login"/></td>
                </tr>
            </table>
        </form>
        </div>
    </body>
</html>
