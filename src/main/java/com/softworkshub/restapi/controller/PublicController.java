package com.softworkshub.restapi.controller;


import com.softworkshub.restapi.entity.User;
import com.softworkshub.restapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userServices.saveNewUser(user);
    }

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

}
