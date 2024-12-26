package com.softworkshub.restapi.repository;

import com.softworkshub.restapi.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//Controller -> Services -> Repository


public interface JournalEntryRepo extends MongoRepository<JournalEntity, ObjectId> {


}
