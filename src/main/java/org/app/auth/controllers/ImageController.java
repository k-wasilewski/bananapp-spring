package org.app.auth.controllers;

import com.google.gson.Gson;
import org.app.auth.POJOs.Prediction;
import org.app.auth.entities.Image;
import org.app.auth.services.ImageService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@Controller
@CrossOrigin(origins = "http://localhost:8083")
public class ImageController {
    private ImageService imageService;

    public ImageController(@Lazy ImageService imageService) {this.imageService=imageService;}

    @PostMapping("/auth/saveimg")
    @ResponseBody
    public String createImage(@RequestParam("filename") String filename,
                              @RequestParam("score") Double score,
                              @RequestParam("acc") Double acc,
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
            Prediction prediction =
                    imageService.getPrediction(filename, username);
            String json = new Gson().toJson(prediction);
            return json;
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping(value = "/auth/del", method = RequestMethod.POST)
    @ResponseBody
    public void delImage(@RequestParam("filename") String filename,
                         @RequestParam("username") String username) {
        String systemuser = System.getProperty("user.name");
        String APP_PATH = "/home/"+systemuser+"/bananapp/auth";

        imageService.delImage(filename, username);

        String filepath = APP_PATH + File.separator + username + File.separator + filename;
        File file = new File(filepath);
        System.out.println(file.exists());
        if (file.exists()) file.delete();
    }
}