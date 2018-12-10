
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
            <p><b>ราคา: ${book.unitprice}</b></p>
            <form action = "AddToCart">
                <input type = "hidden" name = "isbn" value = "${book.isbn}">
                <input type = "submit" class="btn btn-secondary" value = "Add To Cart">
            </form>
        </div>

        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
