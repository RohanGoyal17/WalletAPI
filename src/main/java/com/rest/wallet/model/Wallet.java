package com.rest.wallet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;
import com.rest.user.model.Users;

@Entity
public class Wallet {

    @Id                         //defining id field (int in this case)
    @Column(name = "phone")
    private Integer phone;
    @Column(name = "balance")
    private Integer balance;
    //@ManyToOne
    //private Users users;

    public Wallet() {
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}