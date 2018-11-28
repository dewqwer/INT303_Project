<%-- 
    Document   : BookDetail
    Created on : Nov 16, 2018, 7:47:44 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Detail Page</title>
    </head>
    <body>
        <form action="ProductReview" method="post">
             <input type="hidden" name="returnUrl" value="${returnUrl}">
            <input type="hidden" name="isbn" value="${books.isbn}">
            <div class="ShowBookDetail">
                <table>
                    <tr>
                    <img src="images/novel/${books.isbn}.jpg" width="120">
                    </tr>
                    <tr>
                        <td>ISBN</td>
                        <td>${books.isbn}</td>
                    </tr>
                    <tr>
                        <td>Title: </td>
                        <td>${books.title}</td>
                    </tr>
                    <tr>
                        <td>Author: </td>
                        <td>${books.author}</td>
                    </tr>
                    <tr>
                        <td>Publisher: </td>
                        <td>${books.publisher}</td>
                    </tr>
                    <tr>
                        <td>Category: </td>
                        <td>${books.category}</td>
                    </tr>
                    <tr>
                        <td>Price: </td>
                        <td>${books.unitprice}</td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td>${books.description}</td>
                    </tr>
                </table>
            </div>
            <input type="submit" value="review">
        </form>


        <div class="OldReview">
            <table id="example" class="table">
                <thead>
                <th>Review No.</th>
                <th>Comment</th>
                <th>Rating</th>
                <th>User:</th>
                </thead>
                <c:forEach items="${productReviewList}" var="pr">
                    <tr>
                        <td>${pr.reviewid}</td>
                        <td>${pr.comment}</td>
                        <td>${pr.rating}</td>
                        <td>${pr.customerid.firstname}</td>
                    </tr>
                </c:forEach>
            </table>                    
        </div>
    </body>
</body>
</html>
