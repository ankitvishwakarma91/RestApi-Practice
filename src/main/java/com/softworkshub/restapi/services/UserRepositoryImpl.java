package com.softworkshub.restapi.services;

import com.softworkshub.restapi.entity.User;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    public List<User> getUserForSa(){
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is("akash"));

    };

}
