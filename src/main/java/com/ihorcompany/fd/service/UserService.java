package com.ihorcompany.fd.service;

import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void create(User user);
    Optional<User> readById(Long id);
    Optional<User> readByUsername(String username);
//    Optional<User> update(User user);
    void deleteById(Long id);
    void deleteByUsername(String username);
    List<User> findAll();
    void registerNewUser(UserDTO userDTO);
}
