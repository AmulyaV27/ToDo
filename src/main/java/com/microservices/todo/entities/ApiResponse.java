package com.microservices.todo.entities;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private boolean status;
}