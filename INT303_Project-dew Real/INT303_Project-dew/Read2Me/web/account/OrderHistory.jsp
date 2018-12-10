
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Order History</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>
        <a href="OrderDetail.jsp"></a>

        <c:choose>
            <c:when test = "${!empty orders}">
                <table>
                    <tr>
                        <th>Order no.</th>
                        <th>Order date</th>
                        <th>Status</th>
                        <th>Total</th>
                    </tr>
                    <c:forEach items = "${orders}" var = "order">
                        <tr>
                            <td>${order.orderid}</td>
                            <td>${fn:substring(order.ordereddate, 0, 9)}</td>
                            <td>${order.ordersatus}</td>
                            <td>${order.payment.totalprice}</td>
                            <th><a href = "OrderDetail?orderid=${order.orderid}">> View Detail</a></th>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <h2>You haven't ordered anything yet.</h2>
                <a href = "/Read2Me">Continue Shopping</a>
            </c:otherwise>
        </c:choose>

        <jsp:include page = "../include/Footer.jsp"/>
</html>
