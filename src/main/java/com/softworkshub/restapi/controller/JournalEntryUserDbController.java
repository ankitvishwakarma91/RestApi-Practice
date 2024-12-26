package com.softworkshub.restapi.controller;

import com.softworkshub.restapi.entity.JournalEntity;
import com.softworkshub.restapi.entity.User;
import com.softworkshub.restapi.repository.UserRepository;
import com.softworkshub.restapi.services.JournalEntryServices;
import com.softworkshub.restapi.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryUserDbController {

    @Autowired
    private JournalEntryServices journalEntryServices;

    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public ResponseEntity<?> getAllJournalEntriesByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findByUserName(userName);
        List<JournalEntity> all = user.getJournalEntityList();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity journalEntity) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User user = userServices.findByUserName(userName);
            journalEntryServices.saveEntry(journalEntity, userName);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findByUserName(userName);
        List<JournalEntity> collect = user.getJournalEntityList().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<JournalEntity> journalEntity = Optional.ofNullable(journalEntryServices.getJournalEntry(myId));
            if (journalEntity.isPresent()) {
                return new ResponseEntity<>(journalEntity.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean b = journalEntryServices.deleteJournalEntry(myId, userName);
        if (b){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournal(
            @PathVariable ObjectId id,
            @RequestBody JournalEntity newEntry
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userServices.findByUserName(userName);
        List<JournalEntity> collect = user.getJournalEntityList().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
        if (!collect.isEmpty()) {
            JournalEntity journalEntity = journalEntryServices.getJournalEntry(id);
            if (journalEntity != null) {
                JournalEntity old = journalEntity;
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
                old.setMessage(newEntry.getMessage() != null && !newEntry.getMessage().isEmpty() ? newEntry.getMessage() : old.getMessage());
                journalEntryServices.saveEntry(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
