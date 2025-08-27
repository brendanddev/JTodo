package com.example.jtodo.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class TodoDAOTest {
    private TodoDAO dao;

    @BeforeEach
    void setUp() {
        dao = new TodoDAO();
    }

    @Test
    void testAddAndGetTodo() {
        Todo todo = new Todo();
        todo.setTitle("Unit Test");
        todo.setDescription("Testing add and get");
        todo.setStatus("Pending");
        dao.addTodo(todo);
        List<Todo> todos = dao.getAllTodos();
        assertTrue(todos.stream().anyMatch(t -> "Unit Test".equals(t.getTitle())));
    }

    @Test
    void testUpdateTodo() {
        Todo todo = new Todo();
        todo.setTitle("Update Test");
        todo.setDescription("Before update");
        todo.setStatus("Pending");
        dao.addTodo(todo);
        List<Todo> todos = dao.getAllTodos();
        Todo added = todos.stream().filter(t -> "Update Test".equals(t.getTitle())).findFirst().orElse(null);
        assertNotNull(added);
        added.setDescription("After update");
        dao.updateTodo(added);
        Todo updated = dao.getTodoById(added.getId());
        assertEquals("After update", updated.getDescription());
    }

    @Test
    void testDeleteTodo() {
        Todo todo = new Todo();
        todo.setTitle("Delete Test");
        todo.setDescription("To be deleted");
        todo.setStatus("Pending");
        dao.addTodo(todo);
        List<Todo> todos = dao.getAllTodos();
        Todo added = todos.stream().filter(t -> "Delete Test".equals(t.getTitle())).findFirst().orElse(null);
        assertNotNull(added);
        dao.deleteTodo(added.getId());
        Todo deleted = dao.getTodoById(added.getId());
        assertNull(deleted);
    }
}
