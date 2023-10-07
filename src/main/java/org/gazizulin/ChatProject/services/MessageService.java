package org.gazizulin.ChatProject.services;

import jakarta.transaction.Transactional;
import org.gazizulin.ChatProject.models.Message;
import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.repositories.MessagesRepository;
import org.gazizulin.ChatProject.repositories.MyUserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Service
public class MessageService {

    private final MessagesRepository messagesRepository;

    private final MyUserRepository myUserRepository;

    @Autowired
    public MessageService(MessagesRepository messagesRepository, MyUserRepository myUserRepository) {
        this.messagesRepository = messagesRepository;
        this.myUserRepository = myUserRepository;
    }

    public List<Message> getAll(){
        List<Message> messages = messagesRepository.findAll();
        messages.forEach(message -> Hibernate.initialize(message.getSender()));
        return messages;
    }

    @Transactional
    public void save(Message message, User user){
        message.setDate(new Date());
        Hibernate.initialize(user.getMessages());
        System.out.println(user);
        user.getMessages().add(message);
        myUserRepository.save(user);
        message.setSender(user);
        messagesRepository.save(message);
    }
}
