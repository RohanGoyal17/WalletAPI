package com.rest.transaction.repository;

import com.rest.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {       //defining database (object type, id type)
    public List<Transaction> findByTransactionid(Integer transactionid); //find by email
    public List<Transaction> findBySenderphone(Integer senderphone);
    public List<Transaction> findByReceiverphone(Integer receiverphone);
    //public List<Users> findById(String id);
    //public List<Wallet> findByPhone(Integer phone); //find by phone number
}