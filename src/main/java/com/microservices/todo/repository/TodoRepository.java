package com.microservices.todo.repository;

import com.microservices.todo.entities.Todo;
import com.microservices.todo.entities.TodoDTO;
import com.microservices.todo.entities.User;
import com.microservices.todo.entities.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByPriority(String priority);
    List<Todo> findByIsCompleted(boolean isCompleted);
    List<Todo> findByDueBy(Date dueBy);
    List<Todo> findByUser(User user);
    long countByUserAndIsCompleted(User user,boolean isCompleted);
    long countByUser(User user);
}
