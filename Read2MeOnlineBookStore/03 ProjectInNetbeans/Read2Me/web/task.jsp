<%-- 
    Document   : Task
    Created on : Sep 17, 2018, 2:08:35 PM
    Author     : INT303
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>User: <a href="Logout">${account.username}</a></h1>
        <p>New task : </p>
        <form action="Task" method="post">
            <input type="text" name="newtask"/>
            <input type="submit" value="Add" />
        </form>
        <table>
            <tr>
                <th>Date</th>
                <th>Task</th>
                <th>Status</th>
            </tr>
            <c:forEach var="t" varStatus="vs" items="${tasklist}" >

                <tr>
                    <td>${t.createdate}</td>
                    <td>${t.task}</td>
                    <td>${t.status}</td>
                </tr>

            </c:forEach>
        </table>
    </body>
</html>
