<%-- 
    Document   : checkout
    Created on : Nov 21, 2018, 10:03:08 PM
    Author     : Dew2018
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="include/Header.jsp"/>
        
        <form action="OrdersDetails" method="post">
            <div name="ShowYourCart">

                <div class="container">
                    <table id="example" class="table">
                        <thead>
                        <th>No.</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Total Price</th>
                        </thead>
                        <c:set var="items" value="${sessionScope.cart.lineItems}"/>
                        <c:set var="bgColorX" value="lightgray"/>
                        <c:set var="bgColorY" value="white"/>

                        <c:forEach items="${items}" var="line" varStatus="lineNumber">
                            <tr style="background-color:${lineNumber.count%2==1?bgColorX:bgColorY}">
                                <td>${lineNumber.count}</td>
                                <td>${line.book.title}</td>
                                <td>${line.quantity}</td>
                                <td><fmt:formatNumber value="${line.book.unitprice}" pattern="#,###.00 " /></td>
                            <td style="text-align:right"><fmt:formatNumber value="${line.totalPrice}" pattern="#,###.00 " /></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2"></td>                     
                            <td>${cart.totalQuantiry}</td>
                            <td></td>
                            <td>${cart.totalPrice}</td>
                        </tr>
                    </table>
                    <input type="submit" value="Next">
                </div>
            </div>

        </form>

    </body>
</html>
