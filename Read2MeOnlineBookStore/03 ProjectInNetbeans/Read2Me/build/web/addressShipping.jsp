<%-- 
    Document   : confirmMyOrder
    Created on : Nov 26, 2018, 7:26:58 PM
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
         <form action="AddressShipping" method="post">
            <div name="ShippingAddress">
                addressNo:<input type="text" name="addressNo" required> 
                street:<input type="text" name="street" required>
                alley:<input type="text" name="alley" required> 
                subdistrict:<input type="text" name="subdistrict" required> 
                district:<input type="text" name="district" required> 
                province:<input type="text" name="province" required> 
                postcode:<input type="text" name="postcode" required> 
            </div>
                    <input type="submit" value="Next">
        </form>
    </body>
</html>
