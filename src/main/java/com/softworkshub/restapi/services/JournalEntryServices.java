package com.softworkshub.restapi.services;

import com.softworkshub.restapi.entity.JournalEntity;
import com.softworkshub.restapi.entity.User;
import com.softworkshub.restapi.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

// Business Logic

@Component
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserServices userServices;

    @Transactional
    public void saveEntry(JournalEntity journalEntity, String userName) {
        User user = userServices.findByUserName(userName);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntity saved = journalEntryRepo.save(journalEntity);
        user.getJournalEntityList().add(saved);
        userServices.saveUser(user);
    }

    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepo.save(journalEntity);
    }


    public List<JournalEntity> getAllJournalEntries() {
        return journalEntryRepo.findAll();
    }

    public JournalEntity getJournalEntry(ObjectId id) {
        return journalEntryRepo.findById(id).orElse(null);
    }

    @Transactional
    public boolean deleteJournalEntry(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userServices.findByUserName(userName);
            removed = user.getJournalEntityList().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userServices.saveUser(user);
                journalEntryRepo.deleteById(id);
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting journal entry", e);
        }
        return removed;
    }

    public JournalEntity updateJournalEntry(ObjectId id, JournalEntity journalEntity) {
        return journalEntryRepo.save(journalEntity);
    }


}
