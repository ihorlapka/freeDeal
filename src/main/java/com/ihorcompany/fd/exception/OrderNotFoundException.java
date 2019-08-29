package com.ihorcompany.fd.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(){
        super("Order not found!");
    }
}
