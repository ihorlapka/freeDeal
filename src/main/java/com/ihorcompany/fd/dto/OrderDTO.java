package com.ihorcompany.fd.dto;

import java.sql.Date;

public class OrderDTO {

    private String ordername;
    private Double payment;
    private Integer dayamount;
    private Integer workersamount;
    private String description;
    private String workpicture;
    private String username;
    private Date date;


    //password
    private String InvalidName;
    private String InvalidPayment;
    private String InvalidDayAmount;
    private String InvalidWorkersAmount;
    private String alreadyExist;

    public OrderDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Integer getDayamount() {
        return dayamount;
    }

    public void setDayamount(Integer dayamount) {
        this.dayamount = dayamount;
    }

    public Integer getWorkersamount() {
        return workersamount;
    }

    public void setWorkersamount(Integer workersamount) {
        this.workersamount = workersamount;
    }

    public String getInvalidName() {
        return InvalidName;
    }

    public void setInvalidName(String invalidName) {
        InvalidName = invalidName;
    }

    public String getInvalidPayment() {
        return InvalidPayment;
    }

    public void setInvalidPayment(String invalidPayment) {
        InvalidPayment = invalidPayment;
    }

    public String getInvalidDayAmount() {
        return InvalidDayAmount;
    }

    public void setInvalidDayAmount(String invalidDayAmount) {
        InvalidDayAmount = invalidDayAmount;
    }

    public String getInvalidWorkersAmount() {
        return InvalidWorkersAmount;
    }

    public void setInvalidWorkersAmount(String invalidWorkersAmount) {
        InvalidWorkersAmount = invalidWorkersAmount;
    }

    public String getAlreadyExist() {
        return alreadyExist;
    }

    public void setAlreadyExist(String alreadyExist) {
        this.alreadyExist = alreadyExist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkpicture() {
        return workpicture;
    }

    public void setWorkpicture(String workpicture) {
        this.workpicture = workpicture;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "ordername='" + ordername + '\'' +
                ", payment=" + payment +
                ", dayamount=" + dayamount +
                ", workersamount=" + workersamount +
                ", description='" + description + '\'' +
                ", workpicture='" + workpicture + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
