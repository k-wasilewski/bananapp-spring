package org.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FilesController {
    @Autowired
    ImageService imageService;

    @RequestMapping(value = "/auth/files", method = RequestMethod.GET)
    @ResponseBody
    public List<String> files(Principal principal) {
        List<Image> images = imageService.getImagesByUsername(principal.getName());

        List<String> list = new ArrayList<>();
        for (Image i : images) {
            list.add(i.getFilename()+",,,"+i.getLink());
        }
        return list;
    }
}