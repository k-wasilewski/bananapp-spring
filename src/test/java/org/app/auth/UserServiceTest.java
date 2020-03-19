package org.app.auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;

    @Test
    public void findByUserName() {
        assertEquals("test@test.pl",
                userService.findByUserName("test@test.pl").getUsername());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("user1");
        user.setPassword("pwd");
        userService.saveUser(user);

        assertEquals("user1", userService.findByUserName("user1").getUsername());
    }
}
