package com.microservices.todo.Exceptions;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(long id){
        super("Todo not found for id: "+id);
    }
}
