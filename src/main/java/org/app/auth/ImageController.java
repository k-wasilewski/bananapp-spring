package org.app.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {
    private ImageService imageService;

    public ImageController(@Lazy ImageService imageService) {this.imageService=imageService;}

    @PostMapping("/auth/saveimg")
    @ResponseBody
    public String createImage(@RequestParam("filename") String filename,
                            @RequestParam("score") String score,
                            @RequestParam("acc") String acc,
                            @RequestParam("uname") String username) {
        try {
            Image img = new Image();
            img.setFilename(filename);
            img.setScore(score);
            img.setAcc(acc);
            img.setUsername(username);
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
}
