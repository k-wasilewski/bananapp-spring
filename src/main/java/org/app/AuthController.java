package org.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AuthController {

    @RequestMapping(value = "/auth/test", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
        return "only for logged users";
    }
}