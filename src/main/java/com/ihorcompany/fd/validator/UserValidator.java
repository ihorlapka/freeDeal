package com.ihorcompany.fd.validator;

import com.ihorcompany.fd.dto.UserDTO;
import com.ihorcompany.fd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<RegistrationValidator, UserDTO> {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if(userDTO.getEmail().isEmpty() ||
        userDTO.getUsername().isEmpty() ||
        userDTO.getPassword().isEmpty() ||
        userDTO.getRepeatPassword().isEmpty()){
            userDTO.setInvalidEmail(userDTO.getEmail().isEmpty()?"Email cannot be empty!":"");
            userDTO.setInvalidUsername(userDTO.getUsername().isEmpty()?"Username cannot be empty!":"");
            userDTO.setInvalidPassword(userDTO.getPassword().isEmpty()?"Password cannot be empty!":"");
            isValid = false;
        }
        if (!userDTO.getPassword().equals(userDTO.getRepeatPassword())){
            userDTO.setPasswordsDoNotMatch("Passwords do not match!");
            isValid = false;
        }
        if (userDTO.getEmail().length() < 5 || userDTO.getEmail().length() > 100 ||
        !userDTO.getEmail().contains("@") || !userDTO.getEmail().contains(".")){
            userDTO.setInvalidEmail("Wrong email try again");
            isValid = false;
        }
        if (userRepository.existsUserByUsername(userDTO.getUsername())){
            userDTO.setInvalidUsername("This username already exists!");
            isValid = false;
        }
        if (userRepository.existsUserByEmail(userDTO.getEmail())){
            userDTO.setInvalidEmail("This email already exists!");
            isValid = false;
        }
        return isValid;
    }
}
