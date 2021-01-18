package com.rest.user.resource;

import com.rest.user.model.Users;
import com.rest.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;        // defining reference

    @GetMapping(value = "/user")            //get mapping
    public Users getAll(@RequestParam(value = "userId", defaultValue = "") String id) {
        //return usersRepository.findAll();
        return usersRepository.findById(id).get();
    }

    @PostMapping(value = "/user")           // post mapping
    public List<Users> persist(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }

    @PutMapping(value = "/user")            // put mapping
    public List<Users> update(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }

    @DeleteMapping(value = "/user")         // delete mapping
    public void deleteUser(@RequestParam(value = "userId", defaultValue = "") String id) {
        usersRepository.deleteById(id);
    }

}