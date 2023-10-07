package org.gazizulin.ChatProject.services;

import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.repositories.MyUserRepository;
import org.gazizulin.ChatProject.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Timur Gazizulin
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    @Autowired
    public MyUserDetailsService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = myUserRepository.findByUsername(username);
        System.out.println("im here");
        if(user.isEmpty()){
            System.out.println("username not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new MyUserDetails(user.get());
    }
}
