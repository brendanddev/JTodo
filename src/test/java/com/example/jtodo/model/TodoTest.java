package com.example.jtodo.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    @Test
    void testTodoFields() {
        Todo todo = new Todo();
        todo.setId(1);
        todo.setTitle("Test Title");
        todo.setDescription("Test Description");
        todo.setStatus("Pending");
        todo.setCreatedAt("2025-08-28 07:59:32");

        assertEquals(1, todo.getId());
        assertEquals("Test Title", todo.getTitle());
        assertEquals("Test Description", todo.getDescription());
        assertEquals("Pending", todo.getStatus());
        assertEquals("2025-08-28 07:59:32", todo.getCreatedAt());
    }
}
