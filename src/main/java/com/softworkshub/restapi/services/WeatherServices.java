package com.softworkshub.restapi.services;


import com.softworkshub.restapi.Response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherServices {


    private static final String API_KEY = "86b3328105f8faddeaf88eb5596068e5";


    private static final String BASE_URL = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    // http://api.weatherstack.com/current?access_key=86b3328105f8faddeaf88eb5596068e5&query=Delhi

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getCurrentWeather(String city) {
        String finalApi = BASE_URL.replace("CITY", city).replace("API_KEY", API_KEY);
        ResponseEntity<WeatherResponse> responseEntity = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = responseEntity.getBody();
        return  body;
    }
}
