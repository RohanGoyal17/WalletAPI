package com.rest.transaction.repository;

import com.rest.transaction.model.ElasticTransaction;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//public interface ElasticRepository {

public interface ElasticRepository extends ElasticsearchRepository<ElasticTransaction, Integer> {       //defining database (object type, id type)
    public List<ElasticTransaction> findByTransactionid(Integer transactionid); //find by email
    public List<ElasticTransaction> findBySenderphone(Integer senderphone);
    public List<ElasticTransaction> findByReceiverphone(Integer receiverphone);
    //public List<Users> findById(String id);
    //public List<Wallet> findByPhone(Integer phone); //find by phone number
}