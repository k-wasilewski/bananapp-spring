package org.app.auth.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AfterLogoutControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AfterLogoutController afterLogoutController;

    @Test
    @WithMockUser(username = "test@test.pl", password = "test", roles = "USER")
    public void afterLogout() throws Exception {
        //when, then
        mockMvc.perform(get("/afterlogout")
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isOk())
                .andExpect(content().string("logoutsuccess"))
                .andReturn();
    }
}
