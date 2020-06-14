package org.app.auth.controllers;

import org.app.auth.entities.User;
import org.app.auth.services.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:8083")
public class UserController {

    private UserService userService;

    public UserController(@Lazy UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create-user")
    @ResponseBody
    public String createUser(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        if (password.equals("") || username.equals("")) {
            return "fail";
        }
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.saveUser(user);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }
}
