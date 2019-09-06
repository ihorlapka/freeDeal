package com.ihorcompany.fd.dto;

import com.ihorcompany.fd.validator.RegistrationValidator;
import org.springframework.validation.annotation.Validated;

@Validated
@RegistrationValidator
public class UserDTO {

    private String username;
    private String firstname;
    private String secondname;
    private String email;
    private String password;
    private String phone;
    private String repeatPassword;
    private Integer age;
    private String profession;
    private String profilepicture;
    private String hobbies;

    //errors
    private String alreadyExistsError;
    private String invalidEmail;
    private String invalidUsername;
    private String invalidPassword;
    private String passwordsDoNotMatch;

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getAlreadyExistsError() {
        return alreadyExistsError;
    }

    public void setAlreadyExistsError(String alreadyExistsError) {
        this.alreadyExistsError = alreadyExistsError;
    }

    public String getInvalidEmail() {
        return invalidEmail;
    }

    public void setInvalidEmail(String invalidEmail) {
        this.invalidEmail = invalidEmail;
    }

    public String getInvalidUsername() {
        return invalidUsername;
    }

    public void setInvalidUsername(String invalidUsername) {
        this.invalidUsername = invalidUsername;
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }

    public void setInvalidPassword(String invalidPassword) {
        this.invalidPassword = invalidPassword;
    }

    public String getPasswordsDoNotMatch() {
        return passwordsDoNotMatch;
    }

    public void setPasswordsDoNotMatch(String passwordsDoNotMatch) {
        this.passwordsDoNotMatch = passwordsDoNotMatch;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public void setProfilepicture(String profilepicture) {
        this.profilepicture = profilepicture;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
