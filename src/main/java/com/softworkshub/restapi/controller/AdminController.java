package com.softworkshub.restapi.controller;


import com.softworkshub.restapi.entity.User;
import com.softworkshub.restapi.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final UserServices userServices;

    @Autowired
    public AdminController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userServices.getAllUsers();
        if (!allUsers.isEmpty()) {
            return new ResponseEntity<>(allUsers,HttpStatus.OK);
        }
        return new ResponseEntity<>(allUsers, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public void createAdmin(@RequestBody User user){
        userServices.saveAdminUser(user);
    }

}
