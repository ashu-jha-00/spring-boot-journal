package net.journalapp.journalartifact.controller;

import net.journalapp.journalartifact.entity.JournalEntry;
import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.service.JournalEntryService;
import net.journalapp.journalartifact.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if(journalEntries != null && !journalEntries.isEmpty()){
            return new ResponseEntity<List<JournalEntry>>(journalEntries ,HttpStatus.OK );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND );
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.saveEntry(myEntry , username);
            return new ResponseEntity<>(myEntry , HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryByID(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUserName(username);
        System.out.println("journalController >>>>>>>>>>>>>>>>> " + user.getUsername());

        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myid)).toList();
        System.out.println("journalController >>>>>>>>>>>>>>>>> " + collect);

        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry = journalEntryService.findById(myid);
            if(journalEntry.isPresent()) {
                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            }
//            return new ResponseEntity<>(collect, HttpStatus.OK); collects all the journal entry of the user
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalByID(@PathVariable ObjectId id ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        boolean deleted = journalEntryService.deleteJournalByID(id , username);
        if(deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournalByID(@PathVariable ObjectId id , @RequestBody JournalEntry journalEntry ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userService.findByUserName(username);
        System.out.println("journalController >>>>>>>>>>>>>>>>> " + user.getUsername());

        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        System.out.println("journalController >>>>>>>>>>>>>>>>> " + collect);

        if(!collect.isEmpty()){
            Optional<JournalEntry> old = journalEntryService.findById(id);

            if (old.isPresent()) {
                JournalEntry oldEntry = old.get(); // Extract the JournalEntry object

                oldEntry.setTitle(journalEntry.getTitle() != null && !journalEntry.getTitle().isEmpty()
                        ? journalEntry.getTitle()
                        : oldEntry.getTitle());

                oldEntry.setContent(journalEntry.getContent() != null && !journalEntry.getContent().isEmpty()
                        ? journalEntry.getContent()
                        : oldEntry.getContent());

                journalEntryService.saveEntry(oldEntry); // Save the updated entry

                return new ResponseEntity<>(oldEntry, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
