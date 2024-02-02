package com.microservices.todo.controllers;

import com.microservices.todo.entities.User;
import com.microservices.todo.entities.UserDTO;
import com.microservices.todo.services.Implementations.UserServiceImpl;
import com.microservices.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/save")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body( this.userService.createUser(userDTO));
    }
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser( @RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(userDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(id));
    }
    @GetMapping("/accuracy/{userId}")
    public ResponseEntity<Double> getRatio(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getAccuracyOfUser(userId));
    }
}
