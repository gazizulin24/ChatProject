package org.gazizulin.ChatProject.controllers;

import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.services.RegistrationService;
import org.gazizulin.ChatProject.util.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Timur Gazizulin
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRegistrationValidator userRegistrationValidator;

    private final RegistrationService registrationService;

    @Autowired
    public AuthController(UserRegistrationValidator userRegistrationValidator, RegistrationService registrationService) {
        this.userRegistrationValidator = userRegistrationValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registrate(@ModelAttribute("user") User user, BindingResult bindingResult){

        userRegistrationValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()){
            return "/auth/registration";
        }
        registrationService.registration(user);
        return "auth/login";
    }
}
