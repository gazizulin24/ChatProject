package org.gazizulin.ChatProject.services;

import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Timur Gazizulin
 */
@Service
public class RegistrationService {


    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }



    @Transactional
    public void registration(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        user.setCanChat(true);
        myUserRepository.save(user);
    }
}
