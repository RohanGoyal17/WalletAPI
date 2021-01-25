package com.rest.transaction.repository;

import com.rest.transaction.model.ElasticTransaction;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//public interface ElasticRepository {
@Repository
public interface ElasticRepository extends ElasticsearchRepository<ElasticTransaction, String> {       //defining database (object type, id type)
   // public List<ElasticTransaction> findByTransactionid(Integer transactionid); //find by email
   public List<ElasticTransaction> findBySenderphone(Integer senderphone);
   public List<ElasticTransaction> findByReceiverphone(Integer receiverphone);
}