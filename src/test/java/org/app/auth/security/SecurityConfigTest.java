package org.app.auth.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "test@test.pl", password = "test", roles = "USER")
    public void authPathAccess() throws Exception {
        mockMvc.perform(get("/auth/files")
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isOk())
                .andReturn();

        mockMvc.perform(get("/logout")
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isNoContent())
                .andReturn();

        mockMvc.perform(get("/auth/files")
                .header("Origin", "http://localhost:8083"))
                .andExpect(status().isUnauthorized())
                .andReturn();
    }
}
