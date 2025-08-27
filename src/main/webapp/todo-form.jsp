<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Todo Form</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="form-container">
    <h2>${todo != null ? "Edit Todo" : "Add New Todo"}</h2>
    <form action="todos" method="post">
        <input type="hidden" name="action" value="${todo != null ? "update" : "insert"}" />
        <c:if test="${todo != null}">
            <input type="hidden" name="id" value="${todo.id}" />
        </c:if>
        <label>Title:</label>
        <input type="text" name="title" value="${todo != null ? todo.title : ""}" required />
        <label>Description:</label>
        <textarea name="description">${todo != null ? todo.description : ""}</textarea>
        <label>Status:</label>
        <input type="text" name="status" value="${todo != null ? todo.status : ""}" required />
        <div class="form-actions">
            <input class="btn" type="submit" value="${todo != null ? "Update" : "Add"}" />
            <a class="back-btn" href="todos">Back to List</a>
        </div>
    </form>
</div>
</body>
</html>
