//package com.example.tw.todo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class TodoService {
//
//    @Autowired
//    private final TodoRepository todoRepository;
//
//    public TodoService(TodoRepository todoRepository){
//        this.todoRepository = todoRepository;
//    }
//
//    public List<Todo> getList(){
//        List<Todo> tasks = new ArrayList<>();
//        for (Todo todo : todoRepository.findAll()) {
//            tasks.add(todo);
//        }
//        return tasks;
//    }
//
//    public long addTask(Todo todo){
//        todoRepository.save(todo);
//        return 1;
//    }
//
//    public void deleteTask(Todo todo){
//        todoRepository.delete(todo);
//    }
//
//    public Todo updateTask(long id ,Todo todo){
//        Todo updateTask = todoRepository.findById(id).get();
//        todoRepository.save(updateTask);
//        return updateTask;
//    }
//
//}
