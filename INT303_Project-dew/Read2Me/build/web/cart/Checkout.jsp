
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://bootswatch.com/_vendor/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://bootswatch.com/_vendor/bootstrap/dist/css/bootstrap.css">
        <title>Checkout</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>
        <div class = "cart">
            <h2>My Cart</h2>
            <span>
                ${cart.totalQuantity} ${cart.totalQuantity == 1 ? "item" : "items"} 
            </span>
            <table>
                <c:set var = "items" value="${sessionScope.cart.lineItems}"/>
                <c:forEach items = "${items}" var = "item">
                    ${lineItem.book.title}
                    <tr>
                        <td class = "firstCol">
                            <span class = "bookInfo"><img src = "images/novel/${item.book.isbn}.jpg" width = "180px" height = "250px"></span>
                            <br>
                            <span class = "bookInfo">${item.book.title}</span>
                            <br>
                        </td>
                        <td class = "secondCol"><fmt:formatNumber value="${item.book.unitprice}" pattern="#,###.00 " /></td>
                    </tr>
                    <br>
                </c:forEach>
                <tr class="shippingCost">
                    <td class = "firstCol">Delivery</td>
                    <td class = "secondCol">
                        <c:choose>
                            <c:when test = "${cart.totalPrice >= 500}">
                                <c:set  var = "shippingCost" value = "0"/>
                                FREE
                            </c:when>
                            <c:otherwise>
                                <c:set  var = "shippingCost" value = "30"/>
                                <fmt:formatNumber type = "number" value="${shippingCost}" pattern = ".00"></fmt:formatNumber>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr class="total">
                    <td class = "firstCol">Total</td>
                    <td class = "secondCol">
                        <fmt:formatNumber value="${cart.totalPrice + shippingCost}" pattern="#,###.00 " />
                    </td>
                </tr>
            </table>
        </div>

        <div class = "shipping">
            <h2 style="color: #643f5a;">Shipping Address</h2>
            <br>
            <c:set value = "${sessionScope.user.addressList}" var = "userAddress" ></c:set>
            <c:choose>
                <c:when test = "${empty userAddress}">
                <form action = "AddAddress" method = "post">
                        <span class = "addressField">
                            <label>Address No:</label>
                            <input type="text" name="addressNo" required>
                        </span>

                        <span class = "addressField">
                            <label>Alley: </label>
                            <input type="text" name="alley" required>
                        </span>

                        <span class = "addressField">
                            <label>Street: </label>
                            <input type="text" name="street" required>
                        </span>

                        <span class = "addressField">
                            <label>Subdistrict: </label>
                            <input type="text" name="subdistrict" required>
                        </span>

                        <span class = "addressField">
                            <label>District: </label>
                            <input type="text" name="district" required>
                        </span>

                        <span class = "addressField">
                            <label>Province: </label>
                            <input type="text" name="province" required>
                        </span>

                        <span class = "addressField">
                            <label>Postcode: </label>
                            <input type="text" name="postcode" required>
                        </span>

                        <span class = "addressField">
                            <input type = "submit" value = "Add Address" class="btn btn-info" style="margin: 20px 0 0 0;">
                        </span>
                </form>
                </c:when>
                <c:otherwise>
                    <form action = "Checkout" method = "post">
                        <c:forEach items = "${userAddress}" var = "address">
                            <div>
                                <span class = "addressInfo">
                                    <input type = "radio" name = "addressId" value = "${address.addressid}" required/>
                                    ${address.addressno}, ${address.alley}, ${address.street} <br>
                                    ${address.subdistrict}, ${address.district}, ${address.province} <br>
                                    ${address.postcode}
                                </span>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <br>
                <br>
                <h2 style="color: #643f5a;">Payment</h2>
                <br>
                <span class = "paymentField">
                    <label>Credit Card No:</label>
                    <input type = "number" min = "1" required> &nbsp;
                    <img src = "images/payment/visa.png" alt = "visa" width = "30px" height = "30px">
                    <img src = "images/payment/mastercard.png" alt = "mastercard" width = "30px" height = "30px">
                </span>
                <span class = "paymentField">
                    <label>CCV:</label>
                    <input type = "number" min = "1" required>
                </span>
                <span class = "paymentField">
                    <label>EXP:</label>
                    <input class = "expDate" type = "number" min = "1" max = "12" required> / <input class = "expDate" type = "number" min = "2018" max = "2024" required>
                </span>

                <input type = "hidden" name = "shippingCost" value = "${shippingCost}">

                <span class="submitButton">
                    <input type = "submit" value = "Confirm" class=" btn btn-danger btn-lg btn-info" style = "padding: 20px">
                </span>
            </form>
        </div>

        <style>
            .cart{
                float: right;
                margin: 50px;
                width: 30%;
            }
            .firstCol{
                margin: 50px 0; 
                padding-right: 50px;
            }
            .secondCol{
                margin: 50px 0; 
                padding-left: 50px;
            }
            .bookInfo, .addressField{
                display: block;
            }
            .shipping, .payment{
                margin: 50px;
                width: 60%;
                border-radius: 30px ;
                padding: 30px 30px 30px;
                background-color: #e99a9a;
                color: #f1ead7;

            }
            .addressField, .paymentField, .addressInfo{
                display: block;
                margin: 10px;
            }
            label{
                width: 150px;
            }
            .addressInfo{
                margin: 10px;
            }
            .submitButton{
                width: 10%; padding: 10px; 
                margin: 0 auto; 
                display: block;
                text-align: center;

            }
            .expDate{
                width: 60px;
            }

            .shippingCost{
                background-color: #f1ead7; 
            }
            .total{
                background-color: #f5debc; 
            }


        </style>

        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
