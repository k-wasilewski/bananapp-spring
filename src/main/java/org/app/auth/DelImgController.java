package org.app.auth;

import org.app.auth.ImageService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class DelImgController {
    private ImageService imageService;

    public DelImgController(@Lazy ImageService imageService) {this.imageService=imageService;}

    @RequestMapping(value = "/auth/del", method = RequestMethod.POST)
    @ResponseBody
    public void files(@RequestParam("filename") String filename,
                      @RequestParam("username") String username) {
        Pattern p = Pattern.compile("\\/([^\\/]*?),");
        Matcher matcher = p.matcher(filename);
        if (matcher.find()){
            imageService.delImage(matcher.group(1), username);
        }
    }
}
