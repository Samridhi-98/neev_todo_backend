package com.example.tw.todo;
import com.example.tw.todo.exceptions.TaskDoesNotExistInTheList;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/todo/")
public class TodoController {

    @Autowired
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/getTask")
//    @ApiResponses(value = {
//        @ApiResponses(code = 200, message = "Successfully get the response"),
//        @ApiResponses(code = 400, message = "Error while fetching data")
//    })
    public List<Todo> getTodo(){
        return todoService.getList();
    }

    @GetMapping("/getTodo/{todoId}")
    public Todo getTodoById(@PathVariable long todoId) throws TaskDoesNotExistInTheList {
        if(!todoService.validateTodoId(todoId)){
            throw new TaskDoesNotExistInTheList();
        }
        return todoService.getTaskById(todoId);
    }

    @PostMapping("/addTodo")
    public ResponseEntity<?>  addTodo(@Validated @RequestBody Todo todo){
        if(todo.getId() > 0){
            todoService.addTodo(todo);
            return new ResponseEntity<>("Task has been added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Invalid task", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteTodo/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable long todoId) throws TaskDoesNotExistInTheList {
        if(!todoService.validateTodoId(todoId)){
            throw new TaskDoesNotExistInTheList();
        }
        Todo task = todoService.getTaskById(todoId);
        todoService.deleteTodo(task);
        return new ResponseEntity<>("Task has been deleted successfully", HttpStatus.OK);
    }
}
