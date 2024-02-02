package com.microservices.todo.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id){
        super("User not found for id: "+id);
    }
}
