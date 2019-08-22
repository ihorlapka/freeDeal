package com.ihorcompany.fd.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found!");
    }
}
