package com.ihorcompany.fd.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "User_Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ordername;
    private Double payment;
    private Integer workersamount;
    private Integer dayamount;
    private String description;
    private String workpicture;
    @Basic
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getWorkersamount() {
        return workersamount;
    }

    public void setWorkersamount(Integer workersamount) {
        this.workersamount = workersamount;
    }

    public Integer getDayamount() {
        return dayamount;
    }

    public void setDayamount(Integer dayamount) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "\n   Order{" +
                "id=" + id +
                ", ordername='" + ordername + '\'' +
                '}';
    }
}
