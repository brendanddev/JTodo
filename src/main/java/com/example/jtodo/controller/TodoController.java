package com.example.jtodo.controller;

import com.example.jtodo.model.Todo;
import com.example.jtodo.model.TodoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TodoController extends HttpServlet {
    private TodoDAO todoDAO;

    public void init() {
        todoDAO = new TodoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertTodo(request, response);
                    break;
                case "delete":
                    deleteTodo(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateTodo(request, response);
                    break;
                case "search":
                    searchToDos(request, response);
                    break;
                default:
                    listTodos(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void listTodos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Todo> todos = todoDAO.selectAllTodos();
        request.setAttribute("todos", todos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        Todo newTodo = new Todo(0, title, description, status, null);
        todoDAO.insertTodo(newTodo);
    response.sendRedirect(request.getContextPath() + "/todos");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/todos");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/todos");
            return;
        }
        Todo existingTodo = todoDAO.selectTodo(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo-form.jsp");
        request.setAttribute("todo", existingTodo);
        dispatcher.forward(request, response);
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        Todo todo = new Todo(id, title, description, status, null);
        todoDAO.updateTodo(todo);
    response.sendRedirect(request.getContextPath() + "/todos");
    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/todos");
            return;
        }
        int id;
        try {
            id = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/todos");
            return;
        }
        todoDAO.deleteTodo(id);
        response.sendRedirect(request.getContextPath() + "/todos");
    }

    private void searchToDos(HttpServletRequest request, HttpServletResponse response) 
            throws SQLException, IOException, ServletException {
        String keyword = request.getParameter("keyword");
        List<Todo> todos = todoDAO.searchTodos(keyword);
        
        request.setAttribute("todos", todos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo-list.jsp");
        dispatcher.forward(request, response);

    }
}
