package com.softworkshub.restapi.controller;


import com.softworkshub.restapi.Response.WeatherResponse;
import com.softworkshub.restapi.entity.User;
import com.softworkshub.restapi.repository.UserRepository;
import com.softworkshub.restapi.services.UserServices;
import com.softworkshub.restapi.services.WeatherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {


    private final UserServices userServices;
    private final UserRepository userRepository;
    private final WeatherServices weatherServices;

    @Autowired
    public UserController(UserServices userServices, UserRepository userRepository, WeatherServices weatherServices) {
        this.userServices = userServices;
        this.userRepository = userRepository;
        this.weatherServices = weatherServices;
    }


    //Put Means Updating the values
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userServices.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userServices.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getResponse() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse currentWeather = weatherServices.getCurrentWeather("Delhi");
        String greeting = "";
        if (currentWeather != null) {
            greeting = "Weather feels like : " + currentWeather.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);

    }

}
