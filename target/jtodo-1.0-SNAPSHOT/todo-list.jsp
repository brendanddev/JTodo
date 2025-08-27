<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Todo List</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>
<div class="container">
    <h2>Todo List</h2>
    <a class="add-btn" href="todos?action=new">Add New Todo</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Status</th>
            <th>Created At</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="todo" items="${todos}">
            <tr>
                <td>${todo.id}</td>
                <td>${todo.title}</td>
                <td>${todo.description}</td>
                <td>${todo.status}</td>
                <td>
                    <c:choose>
                        <c:when test="${not empty todo.createdAt}">
                            <fmt:parseDate value="${todo.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" var="createdDate"/>
                            <fmt:formatDate value="${createdDate}" pattern="MMM dd, yyyy hh:mm:ss a"/>
                        </c:when>
                        <c:otherwise>
                            N/A
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="actions">
                    <form action="todos" method="get" style="display:inline;">
                        <input type="hidden" name="action" value="edit" />
                        <input type="hidden" name="id" value="${todo.id}" />
                        <button type="submit" class="btn edit-btn">Edit</button>
                    </form>
                    <form action="todos" method="get" style="display:inline;" onsubmit="return confirm('Are you sure?')">
                        <input type="hidden" name="action" value="delete" />
                        <input type="hidden" name="id" value="${todo.id}" />
                        <button type="submit" class="btn delete-btn">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
