package org.app.auth;

import org.app.auth.ImageService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class DelImgController {
    private ImageService imageService;

    public DelImgController(@Lazy ImageService imageService) {this.imageService=imageService;}

    @RequestMapping(value = "/auth/del", method = RequestMethod.POST)
    @ResponseBody
    public void files(@RequestParam("filename") String filenamePaths,
                      @RequestParam("username") String username) {
        Pattern p = Pattern.compile("\\/([^\\/]*?),");
        Matcher matcher = p.matcher(filenamePaths);

        String APP_PATH = "/home/kuba/Desktop/CodersLab/spring-and-react/target/classes/public/auth";

        if (matcher.find()){
            String filename = matcher.group(1);
            imageService.delImage(filename, username);

            String filepath = APP_PATH + File.separator + username +
                    File.separator + filename;
            File file = new File(filepath);
            file.delete();
        }
    }
}
