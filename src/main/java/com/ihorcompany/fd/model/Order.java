package com.ihorcompany.fd.model;

import javax.persistence.*;

@Entity
@Table(name = "UserOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ordrername;
    private double payment;
    private int workersamount;
    private int dayamount;
    private String description;
    private String workpicture;
    private String date;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrdrername() {
        return ordrername;
    }

    public void setOrdrername(String ordrername) {
        this.ordrername = ordrername;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public int getWorkersamount() {
        return workersamount;
    }

    public void setWorkersamount(int workersamount) {
        this.workersamount = workersamount;
    }

    public int getDayamount() {
        return dayamount;
    }

    public void setDayamount(int dayamount) {
        this.dayamount = dayamount;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordrername='" + ordrername + '\'' +
                ", payment=" + payment +
                ", workersamount=" + workersamount +
                ", dayamount=" + dayamount +
                ", description='" + description + '\'' +
                ", workpicture='" + workpicture + '\'' +
                '}';
    }
}
