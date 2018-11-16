<%-- 
    Document   : BookList
    Created on : Nov 16, 2018, 7:36:44 AM
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
            <jsp:include page="include/Header.jsp?title=Product Listing::"/>


            <table id="example" class="table">
                <thead>
                <th>Image</th>
                <th>No</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
                <th>Add</th>
                <th>Remove</th>
                </thead>
                <c:forEach items="${books}" var="b" varStatus="vs">
                    <tr>
                        <td><img src="model-images/${p.productcode}.jpg" width="120"></td>
                        <td>${vs.count}</td>
                        <td>${b.title}</td>
                        <td>${b.author}</td>
                        <td>${b.unitpriceperone}</td>
                        <td>
                            <!--                            <form action="AddItemToCart" method="post">
                                                            <input type="hidden" value="${p.productcode}" name="productCode"/>
                                                            <input type="submit" value="Add to Cart"/>
                                                        </form>-->
                            <a href="AddItemToCart?productCode=${b.isnb}">
                                <input type="button" value="Add to cart"/>
                            </a>
                        </td>
                        <td>
                            <a href="RemoveToCart?productCode=${b.isnb}">
                                <input type="button" value="Remove to cart"/>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
    </body>
</html>
