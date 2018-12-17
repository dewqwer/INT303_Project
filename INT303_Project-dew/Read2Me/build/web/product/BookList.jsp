
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
        <title>
            ${title != null ? title : "Search Results"} | Read2Me
        </title>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>
        <jsp:include page = "../include/AdvancedSearch.jsp"/>

        <c:if test = "${searchTerm != null}">
            <h2>Search results for ${searchTerm}</h2>
        </c:if>

        <div class = "bookList">
            <c:choose>
                <c:when test = "${books != null}">
                    <c:if test = "${title != null}">
                        <h2>${title}</h2>
                    </c:if>
                    <c:forEach items = "${books}" var = "book" varStatus = "vs">
                        <div class = "book">
                            <span class = "bookInfo">
                                <a href = "BookDetail?isbn=${book.isbn}">
                                    <img src = "images/novel/${book.isbn}.jpg" width = "360px" height = "511px">
                                </a>
                            </span>
                            <span class = "bookInfo">${book.title}</span>
                            <span class = "bookInfo">${book.author}</span> 
                            <span class = "bookInfo"><fmt:formatNumber type = "number" value="${book.unitprice}" pattern = ".00"></fmt:formatNumber></span> 
                            <span class = "bookInfo">
                                <form action = "AddToCart?returnUrl=${requestScope['javax.servlet.forward.request_uri']}?${requestScope['javax.servlet.forward.query_string']}" method = "post">
                                    <input type = "hidden" name = "isbn" value = "${book.isbn}">
                                    <input type = "submit" class="btn btn-secondary" value = "Add To Cart">
                                </form>
                            </span> 
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <h2>No result.</h2>
                </c:otherwise>
            </c:choose>
        </div>
        <style>
            #advancedSearch{
                float: left;
                margin: 20px;
            }
            .searchField{
                display: block;
                margin-bottom: 10%;
            }
            .bookInfo{
                display: block;
            }
            .book{
                float: left;
                margin: 20px 20px 20px 35px;
            }
        </style>
        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
