<%-- 
    Document   : BookDetail
    Created on : Nov 16, 2018, 7:47:44 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Detail Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>ISBN</td>
                <td>${books.isbn}</td>
            </tr>
            <tr>
                <td>Title: </td>
                <td>${books.title}</td>
            </tr>
            <tr>
                <td>Author: </td>
                <td>${books.author}</td>
            </tr>
            <tr>
                <td>Publisher: </td>
                <td>${books.publisher}</td>
            </tr>
            <tr>
                <td>Category: </td>
                <td>${books.category}</td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>${books.unitprice}</td>
            </tr>
            <tr>
                <td>Description: </td>
                <td>${books.description}</td>
            </tr>
        </table>
    </body>
</body>
</html>
