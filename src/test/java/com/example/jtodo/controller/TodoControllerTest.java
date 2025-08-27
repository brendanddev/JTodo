package com.example.jtodo.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.servlet.http.*;
import org.mockito.Mockito;

class TodoControllerTest {
    @Test
    void testServletMapping() {
        TodoController controller = new TodoController();
        assertNotNull(controller);
    }

    // Example: You can add more tests using Mockito to mock HttpServletRequest/Response
    @Test
    void testDoGetNotNull() throws Exception {
        TodoController controller = new TodoController();
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        assertDoesNotThrow(() -> controller.doGet(request, response));
    }
}
