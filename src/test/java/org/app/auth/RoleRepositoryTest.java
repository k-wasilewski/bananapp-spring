package org.app.auth;

import org.app.auth.entities.Role;
import org.app.auth.repositories.RoleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findByName() {
        //given
        /*"ROLE_USER" is saved in AppInitializator*/

        //when
        Role savedRole = roleRepository.findByName("ROLE_USER");

        //then
        assertEquals("ROLE_USER", savedRole.getName());
    }
}
