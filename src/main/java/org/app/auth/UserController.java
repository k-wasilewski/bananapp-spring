package org.app.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserService userService;

    public UserController(@Lazy UserService userService) {
        this.userService = userService;
    }

    /*public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    @PostMapping("/create-user")
    @ResponseBody
    public String createUser(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
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

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

}
