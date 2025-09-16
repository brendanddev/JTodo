package com.example.jtodo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDAO {

    private static final String INSERT_TODO_SQL = "INSERT INTO todos (title, description, status) VALUES (?, ?, ?)";
    private static final String SELECT_TODO_BY_ID = "SELECT id, title, description, status, created_at FROM todos WHERE id = ?";
    private static final String SELECT_ALL_TODOS = "SELECT * FROM todos";
    private static final String DELETE_TODO_SQL = "DELETE FROM todos WHERE id = ?";
    private static final String UPDATE_TODO_SQL = "UPDATE todos SET title = ?, description = ?, status = ? WHERE id = ?";

        private String jdbcURL;
        private String jdbcUsername;
        private String jdbcPassword;

        public TodoDAO() {
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbName = System.getenv("DB_NAME");
            jdbcUsername = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
            jdbcPassword = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "password";
            if (dbHost != null && dbPort != null && dbName != null) {
                jdbcURL = String.format("jdbc:mysql://%s:%s/%s", dbHost, dbPort, dbName);
            } else {
                jdbcURL = "jdbc:mysql://localhost:3306/jtodo_db";
            }
        }

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertTodo(Todo todo) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO_SQL)) {
            preparedStatement.setString(1, todo.getTitle());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setString(3, todo.getStatus());
            preparedStatement.executeUpdate();
        }
    }

    public Todo selectTodo(int id) {
        Todo todo = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TODO_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                todo = new Todo(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todo;
    }

    public List<Todo> selectAllTodos() {
        List<Todo> todos = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TODOS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                todos.add(new Todo(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public List<Todo> searchTodos(String keyword) {
        List<Todo> todos = new ArrayList<>();
        String sql = "SELECT * FROM todos WHERE title LIKE ? OR description LIKE ? ORDER BY created_at DESC";

        try (Connection connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                todos.add(new Todo(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    public boolean deleteTodo(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_TODO_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateTodo(Todo todo) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_TODO_SQL)) {
            statement.setString(1, todo.getTitle());
            statement.setString(2, todo.getDescription());
            statement.setString(3, todo.getStatus());
            statement.setInt(4, todo.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
