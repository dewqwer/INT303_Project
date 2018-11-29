
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://bootswatch.com/4/minty/bootstrap.css">

<div id = "advancedSearch">

    <form class="form-inline my-2 my-lg-0" action = "SearchBook">
        <div class = "searchBox">
            <label>Keyword</label>
            <input class="form-control mr-sm-2 searchField"  name = "keyword" type = "text"  value = "${param.keyword}">

            <label>Title</label>
            <input class="form-control mr-sm-2 searchField"  name = "keyword" type = "text" value = "${param.title}">

            <label>Author</label>
            <input class="form-control mr-sm-2 searchField"  name = "keyword" type = "text"  value = "${param.author}">

            <label>Publisher</label>
            <input class="form-control mr-sm-2 searchField"  name = "keyword" type = "text" value = "${param.publisher}">

            <label>ISBN</label>
            <input class="form-control mr-sm-2 searchField"  name = "keyword" type = "text"  value = "${param.isbn}">

        <label>Category</label>
        <select name = "category" required class = "searchField btn btn-danger">
            <option value = "all" selected>All</option>
            <option value = "crime-mystery">Crime-Mystery</option>
            <option value = "drama">Drama</option>
            <option value = "fantasy">Fantasy</option>
            <option value = "historical-fiction">Historical-Fiction</option>
            <option value = "horror">Horror</option>
            <option value = "romance">Romance</option>
            <option value = "science-fiction">Science-Fiction</option>
        </select>

        <input type = "hidden" name = "advanced" value = "true">
        <input type = "submit" value = "Search"  class = "searchField btn btn-secondary my-2 my-sm-0" id = "searchButton">
        </div>
    </form>
</div>