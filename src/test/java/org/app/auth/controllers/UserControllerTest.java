package org.app.auth.controllers;

import org.app.auth.entities.User;
import org.app.auth.repositories.UserRepository;
import org.app.auth.services.UserService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @After
    public void destr() {
        userRepository.delete(userRepository.findByUsername("test2@test.pl"));
    }

    @Test
    @WithMockUser(username = "test@test.pl", password = "test", roles = "USER")
    public void createUser() throws Exception {
        //given
        final String username = "test2@test.pl";
        final String password = "test";

        //when
        mockMvc.perform(post("/create-user?username="+username+"&password="+password)
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(content().string("success"))
                .andReturn();
        User savedUser = userService.findByUserName(username);

        //then
        assertNotNull(savedUser);
    }
}
