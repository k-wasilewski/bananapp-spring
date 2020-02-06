package org.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AfterLogoutController {

    @ResponseBody
    @GetMapping("/afterlogout")
    public String logout() {
        return "logoutsuccess";
    }
}
