package org.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SuccessController {

    @ResponseBody
    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
