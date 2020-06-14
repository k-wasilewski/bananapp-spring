package org.app.auth.controllers;

import com.google.gson.Gson;
import org.app.auth.POJOs.File;
import org.app.auth.entities.Image;
import org.app.auth.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8083")
public class FilesController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/auth/files", method = RequestMethod.GET)
    @ResponseBody
    public List<String> files(Principal principal) {
        List<Image> images = imageService.getImagesByUsername(principal.getName());
        List<String> jsonFileList = new ArrayList<>();

        for (Image i : images) {
            File file = new File(i.getFilename(), i.getLink());
            String json = new Gson().toJson(file);
            jsonFileList.add(json);
        }

        return jsonFileList;
    }
}