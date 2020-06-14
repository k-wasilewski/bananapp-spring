package org.app.auth.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8083")
public class AfterLogoutController {

    @ResponseBody
    @GetMapping("/afterlogout")
    public String logout() {
        return "logoutsuccess";
    }
}
