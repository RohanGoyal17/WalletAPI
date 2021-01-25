package com.rest.transaction.service;

import com.rest.transaction.model.ElasticTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.rest.transaction.model.Transaction;


@Service
public class KafkaTransaction {

    String kafkaTopic = "transactionstore";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    public void send(ElasticTransaction message) throws Exception{
        this.kafkaTemplate.send("trans", message);

    }
}

