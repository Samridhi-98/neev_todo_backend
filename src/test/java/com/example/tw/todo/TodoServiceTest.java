package com.example.tw.todo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TodoService todoService;

    @Test
    public void shouldAbleToAddTheTodo() throws Exception {
        Todo task = new Todo(1,"Grocery", "Buy food and snacks",false);
        todoService.addTodo(task);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/todo/addTodo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAbleToDeleteTheTodo() throws Exception {
        Todo task = new Todo(1,"Grocery", "Buy food and snacks",false);
        todoService.deleteTodo(task);

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/todo/deleteTodo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
