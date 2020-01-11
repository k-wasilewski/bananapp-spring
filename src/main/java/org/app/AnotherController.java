package org.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@Controller
public class AnotherController {
    @RequestMapping(value = {"/anothers"})
    public String handleFoo() throws IOException {
        return "redirect:another.html";
    }
}
