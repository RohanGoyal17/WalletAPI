package com.rest.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.annotation.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

@Document(indexName="transac")
public class ElasticTransaction {



    @Id                         //defining id field (int in this case)
    @GeneratedValue
    @Column(name = "transactionid")
    private String transactionid;
    @Column(name = "senderphone")
    private Integer senderphone;
    @Column(name = "receiverphone")
    private Integer receiverphone;
    @Column(name = "amount")
    private Integer amount;

    public ElasticTransaction() {
    }


    public ElasticTransaction(Integer senderphone, Integer receiverphone, Integer amount) {
        this.senderphone = senderphone;
        this.receiverphone = receiverphone;
        this.amount = amount;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public void setSenderphone(Integer senderphone) {
        this.senderphone = senderphone;
    }

    public void setReceiverphone(Integer receiverphone) {
        this.receiverphone = receiverphone;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getTransactionid() {
        return transactionid;
    }

    public Integer getSenderphone() {
        return senderphone;
    }

    public Integer getReceiverphone() {
        return receiverphone;
    }

    public Integer getAmount() {
        return amount;
    }


   }