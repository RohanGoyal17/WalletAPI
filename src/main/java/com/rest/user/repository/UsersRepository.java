package com.rest.user.repository;

import com.rest.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {       //defining database (object type, id type)
    public List<Users> findByEmail(String email); //find by email
    //public List<Users> findById(String id);
    public List<Users> findByPhone(Integer phone); //find by phone number
}