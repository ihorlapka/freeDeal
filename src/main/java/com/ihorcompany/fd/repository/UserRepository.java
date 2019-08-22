package com.ihorcompany.fd.repository;

import com.ihorcompany.fd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);
    void deleteUserByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    @Modifying
    @Query("update User u set u.id = ?1, u.username = ?2, u.firstname = ?3, u.secondname = ?4," +
            "u.email = ?5, u.password = ?6, u.phone = ?7, u.age = ?8, u.profession = ?9," +
            "u.profilepicture = ?10, u.hobbies = ?11")
    void setUserInfoById(Long userId, String username, String firstname, String secondname,
                         String email, String password, String phone, int age, String profession,
                         String profilePicture, String hobbies);
}
