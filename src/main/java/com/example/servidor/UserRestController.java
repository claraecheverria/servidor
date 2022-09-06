package com.example.servidor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/api/register")
    public User registerUser (@RequestBody User user){
        repository.save(user);
        return user;
    }

//    @GetMapping("/api/getAll")
//    public List<User> getAllUsers(){
//        return repository.getAll();
//    }
}
