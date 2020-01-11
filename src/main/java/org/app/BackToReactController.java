package org.app;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BackToReactController {
    @RequestMapping(value = {"/backtoreact"})
    public ResponseEntity handleGet(HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "localhost:8081");
        return new ResponseEntity(headers, HttpStatus.FOUND);
    }
}

