package org.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DelImgController {

    @RequestMapping(value = "/auth/del", method = RequestMethod.POST)
    @ResponseBody
    public void files(@RequestParam("filename") String filename) {

    }
}
