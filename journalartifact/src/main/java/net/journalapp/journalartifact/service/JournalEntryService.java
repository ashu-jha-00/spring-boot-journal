package net.journalapp.journalartifact.service;

import net.journalapp.journalartifact.controller.JournalEntryController;
import net.journalapp.journalartifact.entity.JournalEntry;
import net.journalapp.journalartifact.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalByID(ObjectId id){
        return journalEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal Entry not found with ID: " + id));
    }

    public void deleteJournalByID(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
