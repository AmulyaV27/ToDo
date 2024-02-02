package com.microservices.todo.controllers;

import com.microservices.todo.entities.Todo;
import com.microservices.todo.entities.TodoDTO;
import com.microservices.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @PostMapping("/save")
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.todoService.createTodo(todoDTO));

    }
    @PostMapping("/save/all")
    public ResponseEntity<List<TodoDTO>> createTodos(@RequestBody List<TodoDTO> todos){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.todoService.createTodoList(todos));
    }
    @GetMapping("/all")
    public ResponseEntity<List<TodoDTO>> getTodos(){
        return ResponseEntity.ok(this.todoService.getAllTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.todoService.getTodoId(id));
    }
    @PutMapping("/update")
    public  ResponseEntity<TodoDTO> updateTodo(@RequestBody TodoDTO todoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.todoService.updateTodo(todoDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTodo(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.todoService.deleteTodo(id));
    }
    @GetMapping("/priority")
    public ResponseEntity<List<TodoDTO>> getByPriority(@RequestParam(defaultValue ="high",required = false) String priority){
        return ResponseEntity.ok(this.todoService.getByPriority(priority));
    }
    @GetMapping("/completed")
    public ResponseEntity<List<TodoDTO>> getByCompleted(@RequestParam(defaultValue ="true",required = false) boolean isCompleted){
        return ResponseEntity.ok(this.todoService.getByCompleted(isCompleted));
    }
    @GetMapping("/dueBy")
    public ResponseEntity<List<TodoDTO>> getByDate(@RequestParam(required = true) Date date){
       //System.out.println(date);
        return ResponseEntity.ok(this.todoService.getByDueBy(date));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TodoDTO>> getTodoByUser(@PathVariable("userId") Long userid){
        return ResponseEntity.ok(this.todoService.getTodoByUser(userid));
    }
}
