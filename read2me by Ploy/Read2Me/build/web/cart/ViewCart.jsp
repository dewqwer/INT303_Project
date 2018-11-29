
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Shopping Cart</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>

        <c:choose>
            <c:when test = "${cart != null}">
                <c:set var = "items" value="${sessionScope.cart.lineItems}"/>
                <table>
                    <th>ITEMS (${cart.totalQuantity} ${cart.totalQuantity != 1 ? "items" : "item"})</th>
                    <th>QUANTITY</th>
                    <th>UNIT PRICE</th>
                    <th>TOTAL PRICE</th>

                    <c:forEach items="${items}" var="line">
                        <tr>
                            <td>
                                <a href = "BookDetail?isbn=${line.book.isbn}">
                                    <img src = "images/novel/${line.book.isbn}.jpg" width = "360px" height = "500px">
                                </a>
                                ${line.book.title}
                            </td>
                            <td>
                                <form action = "UpdateCart" method = "post">
                                    <input type = "number" name = "quantity" min = "1" value = "${line.quantity}" required>
                                    <input type = "hidden" name = "isbn" value = "${line.book.isbn}">
                                    <input type = "submit" value = "Edit">
                                </form>
                            </td>
                            <td>${line.book.unitprice}</td>
                            <td><fmt:formatNumber value="${line.totalLinePrice}" pattern="#,###.00 " /></td>
                            <td>
                                <form action = "RemoveFromCart" method = "post">
                                    <input type = "hidden" name = "isbn" value = "${line.book.isbn}">
                                    <input type = "submit" value = "Remove">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td>Total</td>                     
                        <td>${cart.totalQuantity}</td>
                        <td></td>
                        <td><fmt:formatNumber value="${cart.totalPrice}" pattern="#,###.00 " /></td>
                    </tr>
                </table>
                <a href = "Checkout">Checkout</a>
            </c:when>
            <c:otherwise>
                <div>
                    <h2>Your cart is empty.</h2>
                    <a href = "/Read2Me">Continue Shopping</a>
                </div>
            </c:otherwise>
        </c:choose>
        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
