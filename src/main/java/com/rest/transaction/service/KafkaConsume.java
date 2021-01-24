package com.rest.transaction.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.rest.transaction.model.Transaction;
import com.rest.transaction.repository.TransactionRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

@Service
public class KafkaConsume {

    @Autowired
    TransactionRepository transactionRepository;
    @KafkaListener(topics = "trans", groupId = "group-id")
    public void consume(Transaction transaction)
    {
        transactionRepository.save(transaction); //save to normal repo //change for elastic
    }


}
