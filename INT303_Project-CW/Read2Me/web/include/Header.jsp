
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://bootswatch.com/4/minty/bootstrap.css">
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class = "brand">
        <a href = "/Read2Me" title = "Read2Me" class="navbar-brand">
            <img src = "images/header/logo.jpg" width = "200px" height = "200px" alt = "Read2Me Logo">
        </a>
        <h1>Read2Me</h1>
        <h2>The best novel selected for you.</h2>
    </div>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="navbar-brand" href="/Read2Me">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="BookList?saleGroup=NewRelease">New Release</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="BookList?saleGroup=BestSeller">Best Seller</a>
            </li>
        <div class = "menu">
            <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                <button type="button" class="btn btn-success">Shop By Category</button>
                <div class="btn-group" role="group">
                    <button id="btnGroupDrop2" type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></button>
                    <div class="dropdown-menu" aria-labelledby="btnGroupDrop2">
                        <ul>
                            <a href = "BookList?category=Crime-Mystery" class="dropdown-item">Crime-Mystery</a>
                            <a href = "BookList?category=Drama" class="dropdown-item">Drama</a>
                            <a href = "BookList?category=Fantasy" class="dropdown-item">Fantasy</a>
                            <a href = "BookList?category=Historical-Fiction" class="dropdown-item">Historical-Fiction</a>
                            <a href = "BookList?category=Horror" class="dropdown-item">Horror</a>
                           <a href = "BookList?category=Romance" class="dropdown-item">Romance</a>
                            <a href = "BookList?category=Science-Fiction" class="dropdown-item">Science-Fiction</a>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <form class="form-inline my-2 my-lg-0" action = "SearchBook">
            <div class = "searchBox">
                <input class="form-control mr-sm-2"  name = "keyword" type = "text" placeholder = "Search by Title, Author, Publisher, ISBN" size = "45" value = "${param.keyword}">
                <input class="btn btn-secondary my-2 my-sm-0" type = "submit" value = "Search">
            </div>
        </form>
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
                <span style="color: whitesmoke">Hello Guest</span><br>
                <a href = "Login" style="color: whitesmoke">Login</a> |
                <a href = "Register" style="color: whitesmoke">Register</a>
            </c:otherwise>
        </c:choose>
    </div>
    <div class = "shoppingCart">
        <a href = "ViewCart">
            <img src = "images/header/CartIcon.png" width = "40px" height = "40px">
            <span style="color: whitesmoke">${cart.totalQuantity != null ? cart.totalQuantity : 0}</span>
        </a>
    </div>
    </ul>
    </div>



</nav>