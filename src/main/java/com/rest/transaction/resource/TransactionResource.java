package com.rest.transaction.resource;

import com.rest.transaction.model.ElasticTransaction;
import com.rest.transaction.model.Transaction;
import com.rest.transaction.repository.ElasticRepository;
import com.rest.transaction.repository.TransactionRepository;
import com.rest.transaction.service.KafkaTransaction;
import com.rest.user.model.Users;
import com.rest.user.repository.UsersRepository;
import com.rest.wallet.model.Wallet;
import com.rest.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class TransactionResource {

    @Autowired
    TransactionRepository transactionRepository;        // defining reference

    @Autowired
    ElasticRepository elasticRepository;

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    KafkaTransaction kafkaTransaction;

    @Autowired
    UsersRepository usersRepository;

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
                //System.out.println(transaction.getTransactionid());
                transactionRepository.save(transaction);

                sender_phone.get(0).incrementBalance(-transaction.getAmount()); // editing summoned objects
                receiver_phone.get(0).incrementBalance(transaction.getAmount());
                walletRepository.save(sender_phone.get(0));    //saving back the data
                walletRepository.save(receiver_phone.get(0));
                return "transaction successful";
            }
            else return "insufficient funds";
        }
        else return "invalid phone number";
    }
    @PostMapping(value = "/trans")           // post mapping
    public String elastic(@RequestBody final ElasticTransaction transaction) {
        List<Wallet> sender_phone = walletRepository.findByPhone(transaction.getSenderphone());
        List<Wallet> receiver_phone = walletRepository.findByPhone(transaction.getReceiverphone());
        if(!sender_phone.isEmpty() && !receiver_phone.isEmpty()) {
            if(sender_phone.get(0).getBalance() >= transaction.getAmount()) {
                ///change for kafka
                //transactionRepository.save(transaction); // for simple repository
                //elasticRepository.save(transaction);  //for elastic search
                try {
                    kafkaTransaction.send(transaction);
                }catch (Exception e){

                }
                //kafkaTransaction.send("debug1"); //debug
                ///change for kafka
                sender_phone.get(0).incrementBalance(-transaction.getAmount()); // editing summoned objects
                receiver_phone.get(0).incrementBalance(transaction.getAmount());
                walletRepository.save(sender_phone.get(0));    //saving back the data
                walletRepository.save(receiver_phone.get(0));
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

    @GetMapping(value = "/transactions")            //part 3
    public List<Transaction> getPaginated(@RequestParam(value = "userId", defaultValue = "") String uid,
                                          @RequestParam(value = "page", defaultValue = "0") Integer pageno,
                                          @RequestParam(value = "size", defaultValue = "1") Integer pagesize) {
        List<Users> user_list = usersRepository.findByUserid(uid);
        if(!user_list.isEmpty()) {
            int phone_number = user_list.get(0).getPhone();  //get phones from id
            List<Transaction> receiver_list = transactionRepository.findByReceiverphone(phone_number);
            List<Transaction> sender_list = transactionRepository.findBySenderphone(phone_number);
            sender_list.addAll(receiver_list);
            List<Transaction> return_list = sender_list.subList(pageno*pagesize,(pageno+1)*pagesize);
            return return_list;
        }
        else {
            List <Transaction> Dummy = new ArrayList<>();
            return Dummy;
        }
    }
    /*@PostMapping(value = "/elastic")
    public String elasticpost(@RequestBody final ElasticTransaction transaction) {
        elasticRepository.save(transaction);
        return "added to elastic repo";
    }*/
    @GetMapping(value = "/transac")            //elastic search get
    public List<ElasticTransaction> getElastic(@RequestParam(value = "userId", defaultValue = "") String uid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer pageno,
                                               @RequestParam(value = "size", defaultValue = "1") Integer pagesize) {
        List<Users> user_list = usersRepository.findByUserid(uid);
        if(!user_list.isEmpty()) {
            int phone_number = user_list.get(0).getPhone();  //get phones from id
            List<ElasticTransaction> receiver_list = elasticRepository.findByReceiverphone(phone_number);
            List<ElasticTransaction> sender_list = elasticRepository.findBySenderphone(phone_number);
            sender_list.addAll(receiver_list);
            List<ElasticTransaction> return_list = sender_list.subList(pageno*pagesize,(pageno+1)*pagesize);
            return return_list;
        }
        else {
            List <ElasticTransaction> Dummy = new ArrayList<>();
            return Dummy;
        }
    }
}