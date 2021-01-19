package com.rest.wallet.resource;

import com.rest.wallet.model.Wallet;
import com.rest.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WalletResource {

    @Autowired
    WalletRepository walletRepository;        // defining reference

    @GetMapping(value = "/wallet/all")
    public List<Wallet> displayAll(){
        return walletRepository.findAll();
    }

    @PostMapping(value = "/wallet")           // post mapping
    public String persist(@RequestBody final Wallet wallet) {
        List<Wallet> phone_number = walletRepository.findByPhone(wallet.getPhone()); // check for same phone number
        if(phone_number.isEmpty()) {
        walletRepository.save(wallet);
        return "Wallet Created";
        }
        else return "Wallet already exists";
    }

}