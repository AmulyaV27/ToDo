package com.microservices.todo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TodoDTO {
    private long id;
    private String title;
    private String description;
    private Date dueBy;
    private String priority;
    private boolean isCompleted;
    private UserDTO user;
}
