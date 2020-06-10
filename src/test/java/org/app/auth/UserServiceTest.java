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
        testUser.setUsername("user_service_user1");
        testUser.setPassword("abc");
        userService.saveUser(testUser);

        assertEquals("user_service_user1",
                userService.findByUserName("user_service_user1").getUsername());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("user_service_user2");
        user.setPassword("pwd");
        userService.saveUser(user);

        assertEquals("user_service_user2",
                userService.findByUserName("user_service_user2").getUsername());
    }
}
