package com.example.tw.todo;

import com.example.tw.exceptions.TaskDoesNotExistInTheList;
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

    public void addTask(Todo todo){
        todoRepository.save(todo);
    }

    public void deleteTask(Todo todo) throws TaskDoesNotExistInTheList {
        if(!todoRepository.existsById(todo.getId())){
            throw new TaskDoesNotExistInTheList();
        }
        todoRepository.delete(todo);
    }

    public Todo updateTask(Todo todo){
        Todo updateTask = todoRepository.findById(todo.getId()).get();
        updateTask.setTitle(todo.getTitle());
        updateTask.setDescription((todo.getDescription()));
        todoRepository.save(updateTask);
        return updateTask;
    }
}
