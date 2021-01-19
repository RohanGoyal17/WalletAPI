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
    public String persist(@RequestBody final Users users) {
        //if(usersRepository.findById(users.getId()) == null)
        List<Users> email_id = usersRepository.findByEmail(users.getEmail());  //check for same email
        List<Users> phone_number = usersRepository.findByPhone(users.getPhone()); // check for same phone number
        List<Users> user_id = usersRepository.findByUserid(users.getUserid());
        if(email_id.isEmpty() && phone_number.isEmpty() && user_id.isEmpty()) {
            usersRepository.save(users);
            return "User Added";
        }
        else return "Id/Email/Phone exists";
    }

    @PutMapping(value = "/user")            // put mapping
    public String update(@RequestBody final Users users ,@RequestParam(value = "userId", defaultValue = "") String id) {

        //List<Users> email_id = usersRepository.findByEmail(users.getEmail());  //check for same email
        //List<Users> phone_number = usersRepository.findByPhone(users.getPhone()); // check for same phone number
        List<Users> user_id = usersRepository.findByUserid(users.getUserid());

        if(!user_id.isEmpty()) {    //checks if user exists
            usersRepository.save(users);
            //return usersRepository.findAll();
            return "User Updated";
        }
        else return "User doesn't exist";
    }

    @DeleteMapping(value = "/user")         // delete mapping
    public String deleteUser(@RequestParam(value = "userId", defaultValue = "") String id) {
        List<Users> user_id = usersRepository.findByUserid(id);
        if(!user_id.isEmpty()) {    //checks if user exists
            usersRepository.deleteById(id);
            return "User deleted";
        }
        else return  "User doesn't exist";
    }

}