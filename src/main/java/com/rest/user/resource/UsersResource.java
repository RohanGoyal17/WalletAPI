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

    @GetMapping(value = "/all")
    public List<Users> displayAll(){
        return usersRepository.findAll();
    }

    @GetMapping(value = "/user")            //get mapping
    public Users getAll(@RequestParam(value = "userId", defaultValue = "") String id) {
        //return usersRepository.findAll()
        return usersRepository.findById(id).get();
    }

    @PostMapping(value = "/user")           // post mapping
    public void persist(@RequestBody final Users users) {
        //if(usersRepository.findById(users.getId()) == null)
        List<Users> email_id = usersRepository.findByEmail(users.getEmail());  //check for same email
        List<Users> phone_number = usersRepository.findByPhone(users.getPhone()); // check for same phone number
        if(email_id.isEmpty() && phone_number.isEmpty()) {
            usersRepository.save(users);
        }
    }

    @PutMapping(value = "/user")            // put mapping
    public void update(@RequestBody final Users users ,@RequestParam(value = "userId", defaultValue = "") String id) {
        if(usersRepository.findById(id).get() != null) {    //checks if user exists
            usersRepository.save(users);
            //return usersRepository.findAll();
        }
    }

    @DeleteMapping(value = "/user")         // delete mapping
    public void deleteUser(@RequestParam(value = "userId", defaultValue = "") String id) {
        if(usersRepository.findById(id).get() != null) {    //checks if user exists
            usersRepository.deleteById(id);
        }
    }

}