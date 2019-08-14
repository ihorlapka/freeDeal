package com.ihorcompany.fd.repository;

import com.ihorcompany.fd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    void deleteUserByUsername(String username);
    //hasn't realized method update;
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);

}
