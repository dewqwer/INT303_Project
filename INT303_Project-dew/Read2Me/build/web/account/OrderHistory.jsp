
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                    <tr class="headerTable">
                        <th>Order no.</th>
                        <th>Order date</th>
                        <th>Status</th>
                        <th>Total</th>
                    </tr>
                    <c:set var="bgColorX" value="#e8e8ea"/>
                    <c:set var="bgColorY" value="#f4f0ec"/>
                    <c:forEach items = "${orders}" var = "order" varStatus="vs">
                        <tr style="color:#0c0d10 ;background-color:${vs.count%2==1?bgColorX:bgColorY}">
                            <td hidden>${vs.count}</td>
                            <td>${order.orderid}</td>
                            <td>${fn:substring(order.ordereddate, 0, 9)}</td>
                            <td>${order.ordersatus}</td>
                            <td><fmt:formatNumber value="${order.payment.totalprice}" pattern="#,###.00 " /></td>
                            <th><a class="viewDetail" href = "OrderDetail?orderid=${order.orderid}">> View Detail</a></th>
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

        <style>
            table{
                background-color:#2d2e3b;
                text-align:center;
                margin:50px;
                width:50%;
                color: #c5c1bd;
            }
            tr{
                width:50%;
                height:50px;
                
            }
            .viewDetail{
               color: #0d9aa2; 
                
                
            }



        </style>

    </body>
</html>
