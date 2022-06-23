package com.example.tw.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    TodoService todoService;

    private List<Todo> todo;

    @BeforeEach
    public void setup(){
        todo = new ArrayList<>();
        todo.add(new Todo(1,"Code", "working for 9-5",false));
        todo.add( new Todo(2,"Sleep", "working for 9-5",false));
        todo.add(new Todo(3,"Eat", "working for 9-5",false));
        todo.add(new Todo(4,"Repeat", "working for 9-5",false));
    }

    @AfterEach
    public void cleanup(){
        todo.clear();
    }


    @Test
    public void shouldAbleToGetAllTheTodo() throws Exception {
        Mockito.when(todoService.getList()).thenReturn(todo);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/todo/getTodo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(equalTo(todo.size())))
                );
    }

    @Test
    public void shouldAbleToGetTodoById() throws Exception {
        Todo task = todo.get(0);
        Mockito.when(todoService.getTaskById(1)).thenReturn(task);

        System.out.println("Task title: " + task.getTitle());
        System.out.println("Task id: " + task.getId());
        System.out.println("Todo size: " + todoService.getTaskById(1).toString());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/todo/getTodo/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is(equalTo(task.getTitle())))
        );
    }

    @Test
    public void shouldAbleToAddTheTodo(){

    }

    @Test
    public void shouldAbleToDeleteTheTodo(){

    }

    @Test
    public void shouldAbleToUpdateTheTodo(){

    }

    @Test
    public void shouldThrowExceptionIfTodoDoesNotExistToDelete(){

    }

}
