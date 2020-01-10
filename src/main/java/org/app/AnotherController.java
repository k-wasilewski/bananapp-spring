package org.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
public class AnotherController {
    @RequestMapping("/anothers")
    void handleFoo(HttpServletResponse response) throws IOException {
        response.sendRedirect("another");
    }
}
