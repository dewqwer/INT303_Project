<%-- 
    Document   : OrderList
    Created on : Nov 16, 2018, 8:14:07 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book List Page</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
    </head>
    <body>
         <div class="container">


            <table id="example" class="table">
                <thead>
                <th>No</th>
                <th>Date</th>
                <th>Status</th>
                </thead>
                <c:forEach items="${orders}" var="order" varStatus="vs">
                    <tr>
                        <td><img src="model-images/${p.productcode}.jpg" width="120"></td>
                        <td>${vs.count}</td>
                        <td>${order.ordereddate}</td>
                        <td>${order.orderstatus}</td>
 
                </c:forEach>
            </table>
        </div>
    </body>
</html>
