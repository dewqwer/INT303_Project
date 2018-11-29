
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class = "brand">
    <a href = "/Read2Me" title = "Read2Me">
        <img src = "images/header/logo.jpg" width = "200px" height = "200px" alt = "Read2Me Logo">
    </a>
    <h1>Read2Me</h1>
    <h2>The best novel selected for you.</h2>
</div>

<div class = "menu">
    <div>
        <a href = "/Read2Me">Home</a>
        <a href = "BookList?saleGroup=NewRelease">New Release</a>
        <a href = "BookList?saleGroup=BestSeller">Best Seller</a>
    </div>
</div>

<div class = "category">
    Shop By Category
    <ul>
        <li><a href = "BookList?category=Crime-Mystery">Crime-Mystery</a></li>
        <li><a href = "BookList?category=Drama">Drama</a></li>
        <li><a href = "BookList?category=Fantasy">Fantasy</a></li>
        <li><a href = "BookList?category=Historical-Fiction">Historical-Fiction</a></li>
        <li><a href = "BookList?category=Horror">Horror</a></li>
        <li><a href = "BookList?category=Romance">Romance</a></li>
        <li><a href = "BookList?category=Science-Fiction">Science-Fiction</a></li>
    </ul>
</div>

<div class = "searchBox">
    <form action = "SearchBook">
        <input name = "keyword" type = "text" placeholder = "Search by Title, Author, Publisher, ISBN" size = "45" value = "${param.keyword}">
        <input type = "submit" value = "Search">
    </form>
</div>

<div class = "account">
    <img src = "images/header/AccountIcon.png" width = "40px" height = "40px" alt = "Account Icon">
    <c:choose>
        <c:when test = "${sessionScope.user != null}">
            <span>Hello ${sessionScope.user.firstname}</span><br>
            <a href = "OrderHistory">Order History</a><br>
            <a href = "MyAddressBook">My Address Book</a><br>
            <a href = "Logout">Logout</a>
        </c:when>
        <c:otherwise>
            <span>Hello Guest</span><br>
            <a href = "Login">Login</a> |
            <a href = "Register">Register</a>
        </c:otherwise>
    </c:choose>
</div>

<div class = "shoppingCart">
    <a href = "ViewCart">
        <img src = "images/header/CartIcon.png" width = "40px" height = "40px">
        <span>${cart.totalQuantity != null ? cart.totalQuantity : 0}</span>
    </a>
</div>