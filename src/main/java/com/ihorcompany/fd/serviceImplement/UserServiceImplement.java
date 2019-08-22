package com.ihorcompany.fd.serviceImplement;

import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.model.Role;
import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.repository.UserRepository;
import com.ihorcompany.fd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> readById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> readByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public void registerNewUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setProfilepicture("pictures/anonymous.png");
        user.setAge(1);
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User update(User user) {
        System.out.println("User username = "+user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void setUserInfoById(Long userId, String username, String firstname, String secondname, String email,
                                String password, String phone, int age, String profession, String profilePicture, String hobbies) {
        userRepository.setUserInfoById(userId,username,firstname,secondname,email,password,phone,age,profession,
                profilePicture,hobbies);
    }
}
