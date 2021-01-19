package com.rest.transaction.repository;

import com.rest.transaction.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {       //defining database (object type, id type)
    //public List<Users> findByEmail(String email); //find by email
    //public List<Users> findById(String id);
    //public List<Wallet> findByPhone(Integer phone); //find by phone number
}