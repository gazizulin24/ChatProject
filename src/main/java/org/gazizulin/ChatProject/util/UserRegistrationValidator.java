package org.gazizulin.ChatProject.util;

import org.gazizulin.ChatProject.models.User;
import org.gazizulin.ChatProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Timur Gazizulin
 */
@Component
public class UserRegistrationValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserRegistrationValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (!userService.hasUserByUsername(user.getUsername())){
            return;
        }

        errors.rejectValue("username", "", "User with same name already exists");


    }
}
