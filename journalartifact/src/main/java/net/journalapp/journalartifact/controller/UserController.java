package net.journalapp.journalartifact.controller;

import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return  userService.getAll();
    }

    @PostMapping
    public User createEntry(@RequestBody User myEntry){
        userService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/id/{id}")
    public Optional<User> getJournalByID(@PathVariable ObjectId id){

        if(id != null)
        return userService.getUserByID(id);
        else return null;
    }



    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
        User userInDb = userService.findByUserName(userName);
        if(userInDb != null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }

    @DeleteMapping("/id/{id}")
    public void deleteJournalByID(@PathVariable ObjectId id){
        userService.deleteUserByID(id);
    }

}
