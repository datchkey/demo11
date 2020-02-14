package com.example.demo.controller;

import com.example.demo.model.documents.User;
import com.example.demo.repository.UserRepository;


import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository  userRepository;
    HashMap<String,String> map = new HashMap<>();

    @GetMapping
    public HashMap<String,String> findUsers() {

        List<User> users= userRepository.findAll();
        for (User user:users) {


//        User user=users.get(0);
            String email = user.getEmail();
            String password = user.getPassword();
            byte[] base = Base64.decode(password);
            String str = new String(base);
            str = email+":"+str;
            String token = Base64.encode(str.getBytes());
            map.put(email, token);
        }
    return map;

    }

}
