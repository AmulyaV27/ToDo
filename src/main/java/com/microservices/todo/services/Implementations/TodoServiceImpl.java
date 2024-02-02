package com.microservices.todo.services.Implementations;

import com.microservices.todo.Exceptions.TodoNotFoundException;
import com.microservices.todo.Exceptions.UserNotFoundException;
import com.microservices.todo.entities.Todo;
import com.microservices.todo.entities.TodoDTO;
import com.microservices.todo.entities.User;
import com.microservices.todo.entities.UserDTO;
import com.microservices.todo.repository.TodoRepository;
import com.microservices.todo.repository.UserRepository;
import com.microservices.todo.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public TodoDTO createTodo(TodoDTO todoDTO) {
        Todo todo= this.modelMapper.map(todoDTO,Todo.class);
        return this.modelMapper.map(this.todoRepository.save(todo),TodoDTO.class);
    }

    @Override
    public List<TodoDTO> createTodoList(List<TodoDTO> todoDTOList) {
        List<Todo> todos = todoDTOList.stream().map(p->this.modelMapper.map(p,Todo.class)).collect(Collectors.toList());
        this.todoRepository.saveAll(todos);
        return todos.stream().map(p->this.modelMapper.map(p,TodoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<TodoDTO> getAllTodos() {
       return this.todoRepository.findAll().stream().map(p->this.modelMapper.map(p,TodoDTO.class)).collect(Collectors.toList());

    }

    @Override
    public TodoDTO getTodoId(Long id) {
        Todo todo= this.todoRepository.findById(id).orElseThrow(()->new TodoNotFoundException(id));
        return this.modelMapper.map(todo,TodoDTO.class);
    }

    @Override
    public TodoDTO updateTodo(TodoDTO todoDTO) {
        Todo todo= this.todoRepository.findById(todoDTO.getId()).orElseThrow(()->new TodoNotFoundException(todoDTO.getId()));
        User user=this.userRepository.findById(todoDTO.getUser().getId()).orElseThrow(()->new UserNotFoundException(todoDTO.getUser().getId()));
        todo.setTitle(todoDTO.getTitle());
        todo.setDescription(todoDTO.getDescription());
        todo.setPriority(todoDTO.getPriority());
        todo.setDueBy(todoDTO.getDueBy());
        todo.setCompleted(todoDTO.isCompleted());
        todo.setUser(user);
        return this.modelMapper.map(this.todoRepository.save(todo),TodoDTO.class);
    }

    @Override
    public boolean deleteTodo(Long id) {
        Todo todo= this.todoRepository.findById(id).orElseThrow(()->new TodoNotFoundException(id));
        this.todoRepository.deleteById(todo.getId());
        return true;
    }

    @Override
    public List<TodoDTO> getByPriority(String priority) {
        List<Todo> todos= this.todoRepository.findByPriority(priority);
        return todos.stream().map(p->this.modelMapper.map(p,TodoDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<TodoDTO> getByCompleted(boolean completed) {
        List<Todo> todos= this.todoRepository.findByIsCompleted(completed);
        return todos.stream().map(p->this.modelMapper.map(p,TodoDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<TodoDTO> getByDueBy(Date dueBy) {
        List<Todo> todos= this.todoRepository.findByDueBy(dueBy);
        return todos.stream().map(p->this.modelMapper.map(p,TodoDTO.class)).collect(Collectors.toList());

    }

    @Override
    public List<TodoDTO> getTodoByUser(Long userId) {
        User user= this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));

        List<Todo> todos=this.todoRepository.findByUser(user);
        return todos.stream().map(p->this.modelMapper.map(p,TodoDTO.class)).collect(Collectors.toList());
    }
}
