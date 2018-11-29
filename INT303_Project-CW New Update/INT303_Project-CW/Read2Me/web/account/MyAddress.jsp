
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>My Address Book</title>
    </head>
    <body>

        <jsp:include page = "../include/Header.jsp"/>

        <h2>My Address Book</h2>

        <c:forEach items = "${addressList}" var = "address">
            <div class = "address">
                <span class = "addressInfo">
                    ${address.addressno}, ${address.alley}, ${address.street} <br>
                    ${address.subdistrict}, ${address.district}, ${address.province} <br>
                    ${address.postcode}
                </span>
            </div>
        </c:forEach>

        <span onclick="show()" style = "border: black solid medium; width: 10%; padding: 10px; margin: 0 auto; display: block;text-align: center; color: cornflowerblue">Add Address</span>


        <div class = "addAddress" id = "add">
            <form action = "AddAddress" method = "post">
                <span class = "addressField">
                    <label>Address No:</label>
                    <input type="text" name="addressNo" required>
                </span>

                <span class = "addressField">
                    <label>Alley: </label>
                    <input type="text" name="alley" required>
                </span>

                <span class = "addressField">
                    <label>Street: </label>
                    <input type="text" name="street" required>
                </span>

                <span class = "addressField">
                    <label>Subdistrict: </label>
                    <input type="text" name="subdistrict" required>
                </span>

                <span class = "addressField">
                    <label>District: </label>
                    <input type="text" name="district" required>
                </span>

                <span class = "addressField">
                    <label>Province: </label>
                    <input type="text" name="province" required>
                </span>

                <span class = "addressField">
                    <label>Postcode: </label>
                    <input type="text" name="postcode" required>
                </span>

                <span class = "addressField">
                    <input type = "submit" value = "Add Address">
                </span>
            </form>
        </div>     
        <style>
            .addAddress, .address{
                margin: 50px auto;
                width: 50%;
                border: black solid thin;
            }
            .addressInfo{
                margin: 10px;
            }
            .addressField{
                margin: 10px auto;
                display: block;
            }
            label{
                width: 150px;
            }
            .addAddress{
                display: none;
                padding: 5px
            }
            .add{
                width:20%;
                margin: 0 auto;
            }
        </style>
        <script>
            function show() {
                document.getElementById("add").style.display = "block";
            }
            ;
        </script>
        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
