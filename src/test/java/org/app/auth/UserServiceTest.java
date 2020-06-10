package org.app.auth;

import org.junit.Before;
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

    @Test
    public void findByUserName() {
        User testUser = new User();
        testUser.setUsername("test@test.pl");
        testUser.setPassword("abc");
        userService.saveUser(testUser);

        assertEquals("test@test.pl",
                userService.findByUserName("test@test.pl").getUsername());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("user12");
        user.setPassword("pwd");
        userService.saveUser(user);

        assertEquals("user12", userService.findByUserName("user12").getUsername());
    }
}
