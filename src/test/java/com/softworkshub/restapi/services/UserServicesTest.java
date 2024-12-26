package com.softworkshub.restapi.services;


import com.softworkshub.restapi.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class UserServicesTest {

    @Autowired
    private UserRepository userRepository;

    @Disabled // It means when you run all program then this function doesn't run
    @Test
    public void add(){
        assertEquals(2,1+1);
        assertNotNull(userRepository.findByUserName("ankit"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2", // 1st test
            "2,3,5",
            "2,3,4"
    })
    public void testParameter(int a, int b , int expected){
        assertEquals(expected,a+b);
    }
}
