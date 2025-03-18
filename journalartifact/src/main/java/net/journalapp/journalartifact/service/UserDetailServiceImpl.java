package net.journalapp.journalartifact.service;

import net.journalapp.journalartifact.entity.User;
import net.journalapp.journalartifact.entity.UserPrincipal;
import net.journalapp.journalartifact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username); // Using correct method name
        System.out.println("UserDetailServiceImpl " + username);
        System.out.println(user);


        if (user == null) {
            System.out.println("Username not found :>>>>>>>>>>>>>>>>>>>>>>>");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new UserPrincipal(user);
    }
}
