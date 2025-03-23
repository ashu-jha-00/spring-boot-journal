package net.journalapp.journalartifact.service;

import lombok.extern.slf4j.Slf4j;
import net.journalapp.journalartifact.entity.JournalEntry;
import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.repository.JournalEntryRepository;
import net.journalapp.journalartifact.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void saveEntry(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER") );
            userRepository.save(user);
        } catch (Exception e){
            log.info("Exception at save entry service");
            log.error("Error occured for {} :" , user.getUsername() , e);
            log.warn("Exception at save entry service");  // only info , error and warn are printed by default
            log.debug("Exception at save entry service"); // debug and trace will not be printed by default
            log.trace("Exception at save entry service");
        }

    }

    public void saveUser(User user){
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

    public User findByUserName(String username){
        return userRepository.findByUsername(username);
    }
}
