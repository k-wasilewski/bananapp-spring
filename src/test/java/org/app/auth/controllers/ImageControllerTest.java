package org.app.auth.controllers;

import org.app.auth.entities.Image;
import org.app.auth.services.ImageService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImageControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ImageService imageService;

    @Test
    @WithMockUser(username = "test@test.pl", password = "test", roles = "USER")
    public void testA_createImage() throws Exception {
        //given
        final String filename = "test.jpg";
        final Double score = 3.0;
        final Double acc = 0.8;
        final String uname = "test@test.pl";
        final String link = "fiuhryufgYGgyu";

        //when
        mockMvc.perform(post("/auth/saveimg?filename="+filename+"&score="+
                        score+"&acc="+acc+"&uname="+uname+"&link="+link)
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("saved at backend"))
                .andReturn();
        Image savedImage = imageService.getImagesByUsername("test@test.pl").get(0);

        //then
        assertEquals(filename, savedImage.getFilename());
        assertEquals(score, savedImage.getScore());
        assertEquals(acc, savedImage.getAcc());
        assertEquals(uname, savedImage.getUsername());
        assertEquals(link, savedImage.getLink());
    }

    @Test
    @WithMockUser(username = "test@test.pl", password = "test", roles = "USER")
    public void testB_getImgPrediction() throws Exception {
        //given
        final String filename = "test.jpg";
        final Double score = 3.0;
        final Double accuracy = 0.8;
        final String username = "test@test.pl";

        //when, then
        mockMvc.perform(post("/auth/imgpred?filename="+filename+"&username="+username)
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(jsonPath("$.score").value(score))
                .andExpect(jsonPath("$.accuracy").value(accuracy))
                .andReturn();
    }

    @Test
    @WithMockUser(username = "test@test.pl", password = "test", roles = "USER")
    public void testC_delImage() throws Exception {
        //given
        final String filename = "test.jpg";
        final String username = "test@test.pl";

        //when
        mockMvc.perform(post("/auth/del?filename="+filename+"&username="+username)
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertTrue(imageService.getImagesByUsername(username).isEmpty());
    }
}
