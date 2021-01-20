package com.rest.transaction.resource;

import com.rest.transaction.model.Transaction;
import com.rest.transaction.repository.TransactionRepository;
import com.rest.user.model.Users;
import com.rest.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.rest.wallet.model.Wallet;

import java.util.List;

@RestController
public class TransactionResource {

    @Autowired
    TransactionRepository transactionRepository;        // defining reference

    @Autowired
    WalletRepository walletRepository;

    @GetMapping(value = "/transaction/all")
    public List<Transaction> displayAll(){
        return transactionRepository.findAll();
    }

    @PostMapping(value = "/transaction")           // post mapping
    public String persist(@RequestBody final Transaction transaction) {
        List<Wallet> sender_phone = walletRepository.findByPhone(transaction.getSenderphone());
        List<Wallet> receiver_phone = walletRepository.findByPhone(transaction.getReceiverphone());
        if(!sender_phone.isEmpty() && !receiver_phone.isEmpty()) {
            if(sender_phone.get(0).getBalance() >= transaction.getAmount()) {
                transactionRepository.save(transaction);
                sender_phone.get(0).incrementBalance(-transaction.getAmount());
                receiver_phone.get(0).incrementBalance(transaction.getAmount());
                return "transaction successful";
            }
            else return "insufficient funds";
        }
        else return "invalid phone number";
    }

    @GetMapping(value = "/transaction")            //part 4
    public String getStatus(@RequestParam(value = "txnId", defaultValue = "") Integer transid) {
        List<Transaction> comparative_transactions = transactionRepository.findByTransactionid(transid);
        if(comparative_transactions.isEmpty())
            return "failed";
        else return "Successfull";
    }
}