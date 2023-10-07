package org.gazizulin.ChatProject.services;

import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Service
public class UserService {

    private final MyUserRepository myUserRepository;

    @Autowired
    public UserService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }


    public User findById(int id){
        return myUserRepository.findById(id).orElse(null);
    }

    public List<User> findAllUsers(){
        return myUserRepository.findAllByRole("ROLE_USER");
    }


    @Transactional
    public void save(User user){
        myUserRepository.save(user);
    }

    @Transactional
    public void setCanChat(int id){
        User user = myUserRepository.findById(id).get();
        user.setCanChat(!user.isCanChat());
        myUserRepository.save(user);
    }


    public boolean hasUserByUsername(String username){
        return myUserRepository.existsByUsername(username);
    }

    public User getGuest(){
        User guest = new User();
        guest.setUsername("Guest");
        guest.setCanChat(false);
        guest.setRole("ROLE_GUEST");
        guest.setPassword("");
        return guest;
    }
}
