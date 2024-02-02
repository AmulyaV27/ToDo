package com.microservices.todo.services.Implementations;

import com.microservices.todo.Exceptions.UserNotFoundException;
import com.microservices.todo.entities.User;
import com.microservices.todo.entities.UserDTO;
import com.microservices.todo.repository.TodoRepository;
import com.microservices.todo.repository.UserRepository;
import com.microservices.todo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO,User.class);
        return this.modelMapper.map(this.userRepository.save(user),UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(p->this.modelMapper.map(p,UserDTO.class)).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user= this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return this.modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user= this.userRepository.findById(userDTO.getId()).orElseThrow(()->new UserNotFoundException(userDTO.getId()));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return this.modelMapper.map(this.userRepository.save(user),UserDTO.class);
    }

    @Override
    public boolean deleteUser(Long id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        this.userRepository.deleteById(user.getId());
        return true;
    }

    @Override
    public double getAccuracyOfUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        Long totalTodos = this.todoRepository.countByUser(user);
        Long completedTodos = this.todoRepository.countByUserAndIsCompleted(user,true);
        System.out.println(totalTodos);
        System.out.println(completedTodos);
        return (double)  completedTodos/totalTodos ;
    }


}
