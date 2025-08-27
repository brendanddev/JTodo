package com.example.jtodo.integration;

import com.example.jtodo.model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class TodoIntegrationTest {
    private TodoDAO dao;

    @BeforeEach
    void setUp() {
        dao = new TodoDAO();
    }

    @Test
    void testAddUpdateDeleteTodoIntegration() {
        // Add
        Todo todo = new Todo();
        todo.setTitle("Integration Test");
        todo.setDescription("Integration test description");
        todo.setStatus("Pending");
        dao.addTodo(todo);
        List<Todo> todos = dao.getAllTodos();
        Todo added = todos.stream().filter(t -> "Integration Test".equals(t.getTitle())).findFirst().orElse(null);
        assertNotNull(added);
        assertEquals("Integration test description", added.getDescription());
        assertEquals("Pending", added.getStatus());

        // Update
        added.setStatus("Completed");
        dao.updateTodo(added);
        Todo updated = dao.getTodoById(added.getId());
        assertEquals("Completed", updated.getStatus());

        // Delete
        dao.deleteTodo(added.getId());
        Todo deleted = dao.getTodoById(added.getId());
        assertNull(deleted);
    }

    @Test
    void testGetAllTodosIntegration() {
        List<Todo> todos = dao.getAllTodos();
        assertNotNull(todos);
        // Optionally check for expected fields
        for (Todo t : todos) {
            assertNotNull(t.getTitle());
            assertNotNull(t.getStatus());
        }
    }
}
