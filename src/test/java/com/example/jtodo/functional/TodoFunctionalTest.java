package com.example.jtodo.functional;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.net.*;
import java.io.*;

class TodoFunctionalTest {
    private final String BASE_URL = "http://localhost:8080/jtodo/todos";

    @Test
    void testListTodosPageLoads() throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(BASE_URL).openConnection();
        conn.setRequestMethod("GET");
        int responseCode = conn.getResponseCode();
        assertEquals(200, responseCode);
        String contentType = conn.getContentType();
        assertTrue(contentType.contains("text/html"));
    }

    @Test
    void testAddTodo() throws Exception {
        String params = "title=Functional+Test&description=Created+by+test&status=Pending&action=add";
        HttpURLConnection conn = (HttpURLConnection) new URL(BASE_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes());
        int responseCode = conn.getResponseCode();
        assertTrue(responseCode == 200 || responseCode == 302); // Redirect or OK
    }

    @Test
    void testEditTodo() throws Exception {
        // This test assumes a todo with id=1 exists
        String params = "id=1&title=Edited+Title&description=Edited+by+test&status=Completed&action=update";
        HttpURLConnection conn = (HttpURLConnection) new URL(BASE_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes());
        int responseCode = conn.getResponseCode();
        assertTrue(responseCode == 200 || responseCode == 302);
    }

    @Test
    void testDeleteTodo() throws Exception {
        // This test assumes a todo with id=1 exists
        String params = "id=1&action=delete";
        HttpURLConnection conn = (HttpURLConnection) new URL(BASE_URL).openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.getOutputStream().write(params.getBytes());
        int responseCode = conn.getResponseCode();
        assertTrue(responseCode == 200 || responseCode == 302);
    }
}
