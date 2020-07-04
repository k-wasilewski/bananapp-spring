package org.app;

import org.app.auth.entities.Role;
import org.app.auth.repositories.RoleRepository;
import org.app.auth.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppInitializerTest {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    public void init() {
        //given
        /*"ROLE_USER" is saved in AppInitializer*/

        //when
        Role savedRole = roleRepository.findByName("ROLE_USER");

        //then
        assertEquals("ROLE_USER", savedRole.getName());
    }
}