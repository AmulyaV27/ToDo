package com.microservices.todo.services;

import com.microservices.todo.entities.TodoDTO;

import java.sql.Date;
import java.util.List;

public interface TodoService {
    TodoDTO createTodo(TodoDTO todoDTO);
    List<TodoDTO> createTodoList(List<TodoDTO> todoDTOList);
    List<TodoDTO> getAllTodos();
    TodoDTO getTodoId(Long id);
    TodoDTO updateTodo(TodoDTO todoDTO);
    boolean deleteTodo(Long id);
    List<TodoDTO> getByPriority(String priority);
    List<TodoDTO> getByCompleted(boolean completed);
    List<TodoDTO> getByDueBy(Date dueBy);
    List<TodoDTO> getTodoByUser(Long userId);
}
