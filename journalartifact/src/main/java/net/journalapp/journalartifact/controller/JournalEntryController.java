package net.journalapp.journalartifact.controller;

import net.journalapp.journalartifact.entity.JournalEntry;
import net.journalapp.journalartifact.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return  journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/id/{id}")
    public JournalEntry getJournalByID(@PathVariable ObjectId id){

        if(id != null)
        return journalEntryService.getJournalByID(id);
        else return null;
    }

    @DeleteMapping("/id/{id}")
    public void deleteJournalByID(@PathVariable ObjectId id){
        journalEntryService.deleteJournalByID(id);
    }

    @PutMapping("/id/{id}")
    public void updateJournalByID(@PathVariable ObjectId id){
        
    }

}
