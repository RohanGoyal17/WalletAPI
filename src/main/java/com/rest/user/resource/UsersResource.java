package com.rest.user.resource;

import com.rest.user.model.Users;
import com.rest.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(value = "/rest/users")
public class UsersResource {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping(value = "/user")
    public Users getAll(@RequestParam(value = "userId", defaultValue = "") String id) {
        //return usersRepository.findAll();
        return usersRepository.findById(id).get();
    }

    @PostMapping(value = "/user")
    public List<Users> persist(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }

    @PutMapping(value = "/user")
    public List<Users> update(@RequestBody final Users users) {
        usersRepository.save(users);
        return usersRepository.findAll();
    }

    @DeleteMapping(value = "/user")
    public void deleteUser(@RequestParam(value = "userId", defaultValue = "") String id) {
        usersRepository.deleteById(id);
    }

}