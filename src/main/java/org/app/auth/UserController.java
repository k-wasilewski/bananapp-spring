package org.app.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private UserService userService;

    public UserController(@Lazy UserService userService) {
        this.userService = userService;
    }

    /*public void setUserService(UserService userService) {
        this.userService = userService;
    }*/

    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("user@user.com");
        user.setPassword("user");
        userService.saveUser(user);
        return "admin";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

}
