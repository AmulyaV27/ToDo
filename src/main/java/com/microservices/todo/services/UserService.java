package com.microservices.todo.services;

import com.microservices.todo.entities.User;
import com.microservices.todo.entities.UserDTO;

import java.util.List;

public interface UserService {
      UserDTO createUser(UserDTO userDTO);
      List<UserDTO> getAllUsers();
      UserDTO getUserById(Long id);
      UserDTO updateUser(UserDTO userDTO);
      boolean deleteUser(Long id);
      double getAccuracyOfUser(Long id);
}
