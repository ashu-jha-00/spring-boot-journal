package net.journalapp.journalartifact.controller;

import net.journalapp.journalartifact.entity.JournalEntry;
import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.service.JournalEntryService;
import net.journalapp.journalartifact.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournal(){
        List<JournalEntry> all = journalEntryService.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK );
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(@PathVariable String username){
        User user = userService.findByUserName(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if(journalEntries != null && !journalEntries.isEmpty()){
            return new ResponseEntity<List<JournalEntry>>(journalEntries ,HttpStatus.OK );

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND );
    }

    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry , @PathVariable String username){
        try {
             journalEntryService.saveEntry(myEntry , username);
             return new ResponseEntity<>(myEntry , HttpStatus.OK);
        }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalEntryByID(@PathVariable ObjectId id){
        JournalEntry journalEntry = journalEntryService.getJournalByID(id);
        if(journalEntry != null){
           return new ResponseEntity<>(journalEntry , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{username}/{id}")
    public ResponseEntity<?> deleteJournalByID(@PathVariable ObjectId id , @PathVariable String username){
        journalEntryService.deleteJournalByID(id , username);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<?> updateJournalByID(@PathVariable ObjectId id , @RequestBody JournalEntry journalEntry , @PathVariable String username){
        JournalEntry old = journalEntryService.getJournalByID(id);
        if(old != null){
           old.setTitle(journalEntry.getTitle()!=null && !journalEntry.getTitle().equals("")? journalEntry.getTitle() : old.getTitle());
           old.setContent(journalEntry.getContent() != null && !journalEntry.getContent().equals("") ? journalEntry.getContent() : old.getContent());
           journalEntryService.saveEntry(old);
           return new ResponseEntity<>(old,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
