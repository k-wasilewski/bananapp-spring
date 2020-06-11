package org.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8083")
public class FilesController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/auth/files", method = RequestMethod.GET)
    @ResponseBody
    public List<String> files(@RequestParam("username") String username) {
        List<Image> images = imageService.getImagesByUsername(username);

        List<String> list = new ArrayList<>();
        for (Image i : images) {
            list.add(i.getFilename()+",,,"+i.getLink());
        }
        return list;
    }
}