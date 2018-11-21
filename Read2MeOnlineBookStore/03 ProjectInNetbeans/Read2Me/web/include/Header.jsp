<%-- 
    Document   : Header
    Created on : Aug 10, 2018, 3:12:01 PM
    Author     : INT303
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-1">
            <a href="index.html" title="Back to Home"><img src="images/header/logo.jpg" width="80"/></a>
        </div>
        <div class="col-sm-3">
            <h4> <br>${param.title}</h4>
        </div>
        <div class="col-sm-6" style="text-align: right;vertical-align: middle">
            <c:if test="${cart!=null}">
                <br><br><a href="ShowCart">Your Cart: (${cart.totalQuantiry})</a>
            </c:if>
        </div>
        <div class="col-sm-2">
            <br><br>
            <c:choose>
                <c:when test="${sessionScope.user != null}">
                    Hello <a href="Logout">${sessionScope.user.contactfirstname},</a>
                </c:when>
                <c:otherwise>
                    Hello <a href="Login">Guest</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>