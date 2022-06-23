package com.example.tw.todo;
import com.example.tw.todo.exceptions.TaskDoesNotExistInTheList;
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

    @GetMapping("/getTodo")
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
    public ResponseEntity<?> addTodo(@Validated @RequestBody Todo todo){
        Long initialCount = todoService.countOfTodo();
        todoService.addTodo(todo);

        if(initialCount + 1 == todoService.countOfTodo() ){
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
        Long initialCount = todoService.countOfTodo();

        todoService.deleteTodo(task);

        if(initialCount - 1 == todoService.countOfTodo()){
            return new ResponseEntity<>("Task has been deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Unable to delete the task", HttpStatus.EXPECTATION_FAILED);
    }

    @PutMapping("/updateTodo/{todoId}")
    public ResponseEntity<?> updateTodo(@PathVariable long todoId, @RequestBody Todo todo) throws TaskDoesNotExistInTheList {
        if(!todoService.validateTodoId(todoId)){
            throw new TaskDoesNotExistInTheList();
        }

        Todo currentTask = todoService.getTaskById(todoId);
        Todo updatedTask = todoService.updateTodo(todoId,todo);

        System.out.println("task current: "+ currentTask);
        System.out.println("task updated: "+ updatedTask);

        if(todoService.compareUpdatedTodoWithPreviousTodo(updatedTask, currentTask)){
            return new ResponseEntity<>("Task has been updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Update request failed", HttpStatus.EXPECTATION_FAILED);
    }
}
