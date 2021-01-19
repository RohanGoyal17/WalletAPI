package com.rest.transaction.resource;

import com.rest.transaction.model.Transaction;
import com.rest.transaction.repository.TransactionRepository;
import com.rest.user.model.Users;
import com.rest.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionResource {

    @Autowired
    TransactionRepository transactionRepository;        // defining reference


    @GetMapping(value = "/transaction/all")
    public List<Transaction> displayAll(){
        return transactionRepository.findAll();
    }

    @PostMapping(value = "/transaction")           // post mapping
    public String persist(@RequestBody final Transaction transaction) {
        transactionRepository.save(transaction);
        return "transaction successful";
    }

}