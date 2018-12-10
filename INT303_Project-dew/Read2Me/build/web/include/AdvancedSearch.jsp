
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id = "advancedSearch">

    <form action = "SearchBook">
        <label>Keyword</label>
        <input type = "text" name = "keyword" value = "${param.keyword}" class = "searchField">

        <label>Title</label>
        <input type = "text" name = "title" value = "${param.title}" class = "searchField">

        <label>Author</label>
        <input type = "text" name = "author" value = "${param.author}" class = "searchField">

        <label>Publisher</label>
        <input type = "text" name = "publisher" value = "${param.publisher}" class = "searchField">

        <label>ISBN</label>
        <input type = "text" name = "isbn" value = "${param.isbn}"class = "searchField">

        <label>Category</label>
        <select name = "category" required class = "searchField">
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
        <input type = "submit" value = "Search"  class = "searchField" id = "searchButton">
    </form>
</div>