package com.example.tw.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public List<Todo> getList(){
        List<Todo> tasks = new ArrayList<>();
        for (Todo todo : todoRepository.findAll()) {
            tasks.add(todo);
        }
        return tasks;
    }

    public Todo getTaskById(long id){
        return todoRepository.findById(id).get();
    }

    public void addTodo(Todo todo){
        todoRepository.save(todo);
    }

    public void deleteTodo(Todo todo){
        todoRepository.delete(todo);
    }

    public Todo updateTodo(Todo todo){
        Todo updateTask = todoRepository.findById(todo.getId()).get();
        updateTask.setTitle(todo.getTitle());
        if(todo.getDescription().length() > 0){
            updateTask.setDescription((todo.getDescription()));
        }
        updateTask.setCompleted(todo.getCompleted());
        updateTask.setCreatedAt(todo.getCreatedAt());
        todoRepository.save(updateTask);
        return updateTask;
    }

    public Boolean validateTodoId(long id){
        return todoRepository.existsById(id);
    }
}
