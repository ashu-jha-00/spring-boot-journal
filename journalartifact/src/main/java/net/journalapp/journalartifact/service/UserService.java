package net.journalapp.journalartifact.service;

import net.journalapp.journalartifact.entity.JournalEntry;
import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.repository.JournalEntryRepository;
import net.journalapp.journalartifact.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getUserByID(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteUserByID(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
