package com.softworkshub.restapi.controller;


import com.softworkshub.restapi.entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
//@RestController
//@RequestMapping("/journal")
//public class JournalEntryController {


//    private Map<Long, JournalEntity> journalEntityMap = new HashMap<Long, JournalEntity>();
//
//
//    @GetMapping
//    public List<JournalEntity> getJournals() {
//        return new ArrayList<>(journalEntityMap.values());
//    }
//
//    @PostMapping()
//    public boolean addJournal(@RequestBody JournalEntity journalEntity) {
//        journalEntityMap.put(journalEntity.getId(), journalEntity);
//        return true;
//    }
//
//    @GetMapping("/id/{myId}")
//    public JournalEntity getJournalById(@PathVariable Long myId) {
//        return journalEntityMap.get(myId);
//    }


//}
