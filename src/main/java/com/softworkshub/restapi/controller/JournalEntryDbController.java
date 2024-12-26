package com.softworkshub.restapi.controller;

import com.softworkshub.restapi.entity.JournalEntity;
import com.softworkshub.restapi.services.JournalEntryServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

//@RestController
//@RequestMapping("/journal")
//public class JournalEntryDbController {
//
//    @Autowired
//    private JournalEntryServices journalEntryServices;
//
//    @GetMapping
//    public List<JournalEntity> getJournals() {
//        return journalEntryServices.getAllJournalEntries();
//    }
//
//    @PostMapping()
//    public ResponseEntity<JournalEntity> addJournal(@RequestBody JournalEntity journalEntity) {
//        try {
//            journalEntity.setDate(LocalDateTime.now());
//            journalEntryServices.saveEntry(journalEntity);
//            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
//        }catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    @GetMapping("/id/{myId}")
//    public ResponseEntity<JournalEntity> getJournalById(@PathVariable ObjectId myId) {
//       Optional<JournalEntity> journalEntity = Optional.ofNullable(journalEntryServices.getJournalEntry(myId));
//        return journalEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @DeleteMapping("id/{myId}")
//    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId) {
//         journalEntryServices.deleteJournalEntry(myId);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @PutMapping("/id/{id}")
//    public JournalEntity updateJournal(@PathVariable ObjectId id ,@RequestBody JournalEntity newEntry) {
//        JournalEntity old = journalEntryServices.getJournalEntry(id);
//        if (old != null) {
//            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
//            old.setMessage(newEntry.getMessage() != null && !newEntry.getMessage().isEmpty() ? newEntry.getMessage() : old.getMessage());
//        }
//        journalEntryServices.saveEntry(old);
//        return old;
//    }

//}
