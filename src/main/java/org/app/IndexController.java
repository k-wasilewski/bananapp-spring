package org.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class IndexController {
    @RequestMapping(value = {"/"})
    public String handleFoo() throws IOException {
        return "redirect:index.jsp";
    }
}
