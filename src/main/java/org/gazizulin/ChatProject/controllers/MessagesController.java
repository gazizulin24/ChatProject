package org.gazizulin.ChatProject.controllers;

import org.gazizulin.ChatProject.models.Message;
import org.gazizulin.ChatProject.services.MessageService;
import org.gazizulin.ChatProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Timur Gazizulin
 */
@Controller
public class MessagesController {
    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public MessagesController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("/new/message")
    public String leaveMessage(@ModelAttribute("newMessage") Message message,
                               @RequestParam(required = true, name="sender") int senderId){
        messageService.save(message, userService.findById(senderId));
        return "redirect:/chat";
    }
}
