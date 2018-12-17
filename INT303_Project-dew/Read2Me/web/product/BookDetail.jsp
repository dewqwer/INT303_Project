
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://bootswatch.com/4/minty/bootstrap.css">
        <title>${book.title} : ${book.isbn}</title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>

        <img src = "images/novel/${book.isbn}.jpg" alt = "${book.isbn}" style = "float:left">
        <div class = "bookInfo">
            <h2>${book.title}</h2>
            <p><b>เรื่องย่อ:</b> ${book.description}</p>
            <p><b>ผู้แต่ง:</b> ${book.author}</p>
            <p><b>สำนักพิมพ์:</b> ${book.publisher}</p>
            <p><b>หมวดหมู่:</b> ${book.category}</p>
            <p><b>ISBN:</b> ${book.isbn}</p>
            <p><b>ราคา: <fmt:formatNumber type = "number" value="${book.unitprice}" pattern = ".00"></fmt:formatNumber></b></p>
            <form action = "AddToCart?returnUrl=${urlBookDetail}?${requestScope['javax.servlet.forward.query_string']}" method="post">
                <input type = "hidden" name = "isbn" value = "${book.isbn}">
                <input type = "submit" class="btn btn-secondary" value = "Add To Cart">
            </form>
        </div>
                
        <div class="sendMyReview">
            <c:if test="${sessionScope.user == null}">
                <form action="Login?returnUrl=${urlBookDetail}?${requestScope['javax.servlet.forward.query_string']}" method="post">
                    <input type = "hidden" name = "returnUrl" value = "${returnUrl}">
                    <textarea name="comment" placeholder="Please Login!" disabled maxlength="150" rows="4" cols="50" style="resize: none ">
                    Please Login!
                    </textarea>
                    
                    <div class="rating">
                        <input type="radio" name="rating" value="1" disabled> 1<br>
                        <input type="radio" name="rating" value="2" disabled> 2<br>
                        <input type="radio" name="rating" value="3" disabled> 3<br>  
                        <input type="radio" name="rating" value="4" disabled> 4<br>  
                        <input type="radio" name="rating" value="5" disabled> 5<br>  
                    </div>
                    <input type = "submit" value = "Login"/>
                </form>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <form action = "Review" method="post">
                    <input type = "hidden" name = "returnUrl" value = "${returnUrl}">
                    <input type = "hidden" name = "isbn" value = "${book.isbn}">
                    <textarea name="comment" placeholder="Your text!" maxlength="150" rows="4" cols="50" style="resize: none ">
                    </textarea>
                        
                    <div class="rating">
                        <input type="radio" name="rating" value="1" > 1<br>
                        <input type="radio" name="rating" value="2" > 2<br>
                        <input type="radio" name="rating" value="3" > 3<br>  
                        <input type="radio" name="rating" value="4" > 4<br>  
                        <input type="radio" name="rating" value="5" > 5<br>  
                    </div>
                    <input type = "submit" value = "Send"/>
                </form>
            </c:if>
        </div>

        <div class="otherReview">
            <c:forEach items="${reviewOfThisBook}" var="reviewOfThisBook" >
                <div class="review">
                    <p><b>Reviewid:</b><span class="review">${reviewOfThisBook.reviewid}</span>
                    <span class="review">${reviewOfThisBook.comment}</span>
                    <p><b>Rating:</b> <span class="review">${reviewOfThisBook.rating}</span>
                    <p><b>Customerid:</b> <span class="review">${reviewOfThisBook.customerid.customerid}</span>
                </div>
            </c:forEach>
        </div>

        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
