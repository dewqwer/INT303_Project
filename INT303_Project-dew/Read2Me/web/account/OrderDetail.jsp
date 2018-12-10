
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Order Detail</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>
        <h2>Order #${order.orderid}</h2>
        <table>
            <tr>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
            </tr>
            <c:forEach items = "${order.lineitemList}" var = "line">
                <tr>
                    <td>${line.book.title}</td>
                    <td>${line.unitprice}</td>
                    <td>${line.quantity}</td>
                    <td>${line.totalLinePrice}</td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
