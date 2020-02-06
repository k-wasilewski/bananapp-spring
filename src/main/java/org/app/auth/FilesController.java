package org.app.auth;

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

    @RequestMapping(value = "/auth/files", method = RequestMethod.GET)
    @ResponseBody
    public List<String> files(Principal principal) {
        File folder = new File("/home/kuba/Desktop/CodersLab/spring-and-react/target/classes/public/auth/"+principal.getName());
        File[] listOfFiles = folder.listFiles();

        List<String> list = new ArrayList<>();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                list.add("auth/"+principal.getName()+"/"+listOfFiles[i].getName());
            }
        }
        return list;
    }
}
