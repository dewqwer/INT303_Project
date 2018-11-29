
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>${title}</title>
        <style>
            #advancedSearch{
                margin: 20px;
            }
            .searchField{
                margin: 20px;
                width: 300px;
            }
            #searchButton{
                display: block;
                text-align: center;
                margin: 0 auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page = "../include/Header.jsp"/>
        <jsp:include page = "../include/AdvancedSearch.jsp"/>
        <jsp:include page = "../include/Footer.jsp"/>
    </body>
</html>
