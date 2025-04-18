package net.journalapp.journalartifact.controller;

import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.repository.UserRepository;
import net.journalapp.journalartifact.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteJournalByID(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepository.deleteByUsername(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
