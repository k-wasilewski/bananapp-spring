package org.app.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@CrossOrigin(origins = "http://localhost:8083")
public class ImageController {
    private ImageService imageService;

    public ImageController(@Lazy ImageService imageService) {this.imageService=imageService;}

    @PostMapping("/auth/saveimg")
    @ResponseBody
    public String createImage(@RequestParam("filename") String filename,
                              @RequestParam("score") String score,
                              @RequestParam("acc") String acc,
                              @RequestParam("uname") String username,
                              @RequestParam("link") String link) {
        try {
            Image img = new Image();
            img.setFilename(filename);
            img.setScore(score);
            img.setAcc(acc);
            img.setUsername(username);
            img.setLink(link.replace("plussign", "+"));
            imageService.saveImage(img);
            return "saved at backend";
        } catch (Exception e) {
            return "failed saving at backend";
        }
    }

    @PostMapping("/auth/imgpred")
    @ResponseBody
    public String getImgPrediction(@RequestParam("filename") String filename,
                                   @RequestParam("username") String username) {
        try {
            return imageService.getPrediction(filename, username);
        } catch (Exception e) {
            return "failed";
        }
    }

    @RequestMapping(value = "/auth/del", method = RequestMethod.POST)
    @ResponseBody
    public void delImage(@RequestParam("filename") String filename,
                         @RequestParam("username") String username) {
        String APP_PATH = "/home/kuba/Desktop/projects/bananapp-react/public/auth";

        imageService.delImage(filename, username);

        String filepath = APP_PATH + File.separator + username + File.separator + filename;
        File file = new File(filepath);
        file.delete();
    }
}