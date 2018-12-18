
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <h2 style="margin:50px;">Order #${order.orderid}</h2>
        <table>
            <tr>
                <th class="title">Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Subtotal</th>
            </tr>
            <c:forEach items = "${order.lineitemList}" var = "line">
                <tr>
                    <td class="title">${line.book.title}</td>
                    <td><fmt:formatNumber value="${line.unitprice}" pattern="#,###.00 " /></td>
                    <td>${line.quantity}</td>
                    <td><fmt:formatNumber value="${line.totalLinePrice}" pattern="#,###.00 " /></td>
                </tr>
            </c:forEach>
        </table>
        <jsp:include page = "../include/Footer.jsp"/>
        
        <style>
            table{
                background-color:#81555c;
                text-align:center;
                margin:50px;
                width:40%;
                color: #c5c1bd;
            }
            td{
                background-color:#e8e8ea;
                border: solid white 3px; 
                color: #696b78;
                
            }
            .viewDetail{
               color: #0d9aa2; 
            }
            tr{
                height:50px;
                width: 50%;
                
            }
        </style>

    </body>
</html>
