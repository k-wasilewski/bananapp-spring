package org.app.auth.controllers;

import org.app.auth.entities.Image;
import org.app.auth.repositories.ImageRepository;
import org.app.auth.repositories.UserRepository;
import org.app.auth.services.ImageService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FilesControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    FilesController filesController;
    @Autowired
    ImageService imageService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    final static String FILENAME = "filename";
    final static String LINK = "abc";

    @Before
    public void init() {
        //given
        Image image = new Image();
        image.setUsername("test@test.pl");
        image.setFilename(FILENAME);
        image.setLink(LINK);
        imageService.saveImage(image);
    }

    @After
    public void destr() {
        imageRepository.delete(
                imageRepository.findAllByUsername("test@test.pl").get(0));
    }

    @Test
    @WithMockUser(username = "test@test.pl", password = "test", roles = "USER")
    public void files() throws Exception {
        //when, then
        mockMvc.perform(get("/auth/files")
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString(FILENAME)))
                .andExpect(content().string(containsString(LINK)))
                .andReturn();
    }
}
