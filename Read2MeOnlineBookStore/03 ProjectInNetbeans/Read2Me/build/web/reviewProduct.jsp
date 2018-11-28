<%-- 
    Document   : reviewProduct
    Created on : Nov 28, 2018, 10:49:30 PM
    Author     : Dew2018
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="ProductReview" method="post">
                    <textarea name="reviewFromUser" style="resize: none;" maxlength="190">
                    </textarea>
                    <input type="number" name="rating" min="1" max="5" required>
                    <input type="submit" value="send">
       </form>
    </body>
</html>
