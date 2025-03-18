package net.journalapp.journalartifact.controller;

import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {

    @Autowired
    UserService userService;
    @GetMapping("/health-check")
    public boolean healthCheck(){
        return true;
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}
