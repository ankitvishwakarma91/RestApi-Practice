package com.softworkshub.restapi.services;

import com.softworkshub.restapi.entity.User;
import com.softworkshub.restapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserDetailsServicesImplTest {


    @InjectMocks
    private UserDetailsServicesImpl userDetailsServicesImpl;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(
                (User) org.springframework.security.core.userdetails.User.builder()
                        .username("akash")
                        .password("aksfshs")
                        .roles("user")
                        .build()
        );
        UserDetails user = userDetailsServicesImpl.loadUserByUsername("akash");
        Assertions.assertNotNull(user);
    }
}
