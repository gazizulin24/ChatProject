package org.gazizulin.ChatProject.controllers;

import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Timur Gazizulin
 */
@Controller
@RequestMapping("/admin")
public class AdminController {


    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String adminPage(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "admin";
    }


    @PostMapping("/blockchat/{id}")
    public String blockChat(@PathVariable int id){
        userService.setCanChat(id);
        return "redirect:/admin";
    }
}
