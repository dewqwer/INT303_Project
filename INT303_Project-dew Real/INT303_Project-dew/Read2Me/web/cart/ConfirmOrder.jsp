
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Order Confirmation</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>
        
        <h2>Order confirmation</h2>
        <table>
            <tr>
                <th>Order Number</th>
                <td>${order.orderid}</td>
            </tr>
            <tr>
                <th>Order Date</th>
                <td>${order.ordereddate}</td>
            </tr>
            <tr>
                <th>Order Total</th>
                <td>${order.payment.totalprice}</td>
            </tr>
            <tr>
                <th>Payment method</th>
                <td>${order.payment.paymentmethod}</td>
            </tr>
            <tr>
                <th>Ship To</th>
                <td>${order.customerid.firstname} ${order.customerid.lastname}<br>
                    ${order.shipping.addressid.addressno} ${order.shipping.addressid.alley} ${order.shipping.addressid.street}
                    ${order.shipping.addressid.subdistrict} ${order.shipping.addressid.district}
                    ${order.shipping.addressid.province}
                </td>
            </tr>
        </table>
        <table>
            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total</th>
            </tr>
            <c:forEach items = "${order.lineitemList}" var = "item">
                <tr>
                    <td>${item.book.title}</td>
                    <td style = "text-align: right">${item.quantity}</td>
                    <td>${item.unitprice}</td>
                    <td>${item.totalLinePrice}</td>
                </tr>
            </c:forEach>
            <tr>
                <th colspan = "2">Item Total</th>
                <th colspan = "1"></th>
                <td>${order.orderTotal}</td>
            <tr>
                <th colspan = "2">Shipping</th>
                <th colspan = "1"></th>
                <td>${order.shipping.shippingcost}</td>
            </tr>
            <tr>
                <th colspan = "2">Grand Total</th>
                <th colspan = "1"></th>
                <td>${order.payment.totalprice}</td>
            </tr>
        </table>
        <style>
            table, h2{
                margin: 20px;
            }
            th, td{
                padding-right: 40px;
            }
        </style>
        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
