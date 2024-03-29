package com.ihorcompany.fd.service;

import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void create(User user);
    Optional<User> readById(Long id);
    Optional<User> readByUsername(String username);
    User update(UserDTO user);
    void deleteById(Long id);
    void deleteByUsername(String username);
    List<User> findAll();
    void registerNewUser(UserDTO userDTO);
    List<User> findAll(Pageable pageable);

}
