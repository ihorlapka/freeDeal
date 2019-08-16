package com.ihorcompany.fd.dto;

public class OrderDTO {

    private String ordername;
    private Double payment;
    private Integer dayamount;
    private Integer workersamount;


    //password
    private String InvalidName;
    private String InvalidPayment;
    private String InvalidDayAmount;
    private String InvalidWorkersAmount;
    private String alreadyExist;

    public OrderDTO() {
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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "ordername='" + ordername + '\'' +
                ", payment=" + payment +
                ", dayamount=" + dayamount +
                ", workersamount=" + workersamount +
                '}';
    }
}
