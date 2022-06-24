package com.example.tw.todo;

import com.example.tw.todo.exceptions.TaskDoesNotExistInTheList;
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

    public Todo updateTodo(Long todoId, Todo updateTodo) throws TaskDoesNotExistInTheList {
        if(!validateTodoId(todoId)){
            throw new TaskDoesNotExistInTheList();
        }
        Todo currentTask = todoRepository.findById(todoId).get();

        currentTask.setTitle(updateTodo.getTitle());
        currentTask.setDescription((updateTodo.getDescription()));
        currentTask.setCompleted(updateTodo.getCompleted());

        todoRepository.save(currentTask);

        return currentTask;
    }

    public Boolean validateTodoId(long id){
        return todoRepository.findById(id).isPresent();
    }

    public Long countOfTodo(){
        return todoRepository.count();
    }

}
