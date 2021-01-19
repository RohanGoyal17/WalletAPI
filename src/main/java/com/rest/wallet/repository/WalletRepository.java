package com.rest.wallet.repository;

import com.rest.wallet.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {       //defining database (object type, id type)
    //public List<Users> findByEmail(String email); //find by email
    //public List<Users> findById(String id);
    public List<Wallet> findByPhone(Integer phone); //find by phone number
}