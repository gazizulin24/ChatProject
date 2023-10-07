package org.gazizulin.ChatProject.controllers;

import org.gazizulin.ChatProject.models.Message;
import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.repositories.MyUserRepository;
import org.gazizulin.ChatProject.security.MyUserDetails;
import org.gazizulin.ChatProject.services.MessageService;
import org.gazizulin.ChatProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;

/**
 * @author Timur Gazizulin
 */
@Controller
public class ChatController {

    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public ChatController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/chat")
    public String chatPage(@ModelAttribute("newMessage")Message message, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!auth.getPrincipal().equals("anonymousUser")) {
            MyUserDetails userDetails = (MyUserDetails) auth.getPrincipal();
            model.addAttribute("user", userDetails.getUser());
        }else{
            model.addAttribute("user", userService.getGuest());
        }
        List<Message> messages = messageService.getAll();
        Collections.reverse(messages);
        model.addAttribute("messages", messages);

        return "chat";
    }
}
