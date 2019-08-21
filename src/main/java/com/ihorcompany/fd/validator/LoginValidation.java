package com.ihorcompany.fd.validator;

import com.ihorcompany.fd.model.User;
import com.ihorcompany.fd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidation implements ConstraintValidator<LoginValidator, User> {

    private UserRepository userRepository;
    //errors
//    private String invalidPassword;
//    private String invalidUsername;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

//        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()){
//            setInvalidUsername(user.getUsername().isEmpty()?"Username cannot be empty!":"");
//            setInvalidPassword(user.getPassword().isEmpty()?"Password cannot be empty!":"");
//            isValid =false;
//        }
//        if (!userRepository.existsUserByUsername(user.getUsername())){
//            setInvalidUsername("There are no user with such Username!");
//            isValid = false;
//        }
//        if ((!userRepository.findUserByUsername(user.getUsername()).get().getPassword().equals(user.getPassword()))){
//            setInvalidPassword("Wrong Password!");
//            isValid = false;
//        }
        return isValid;
    }

//    public String getInvalidPassword() {
//        return invalidPassword;
//    }
//
//    public void setInvalidPassword(String invalidPassword) {
//        this.invalidPassword = invalidPassword;
//    }
//
//    public String getInvalidUsername() {
//        return invalidUsername;
//    }
//
//    public void setInvalidUsername(String invalidUsername) {
//        this.invalidUsername = invalidUsername;
//    }
}
