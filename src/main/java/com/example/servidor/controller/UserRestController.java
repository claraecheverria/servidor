package com.example.servidor.controller;

import com.example.servidor.model.User;
import com.example.servidor.repository.UserRepository;
import com.example.servidor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void registerUser (@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping
    public void prueba (){
        userService.probando();
    }

}
