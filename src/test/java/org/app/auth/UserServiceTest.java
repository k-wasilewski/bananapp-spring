package org.app.auth;

import org.app.auth.entities.User;
import org.app.auth.services.UserService;
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
        //given
        User testUser = new User();
        testUser.setUsername("user_service_user1");
        testUser.setPassword("abc");
        userService.saveUser(testUser);

        //when
        User savedUser = userService.findByUserName("user_service_user1");

        //then
        assertEquals("user_service_user1", savedUser.getUsername());
    }

    @Test
    public void saveUser() {
        //given
        User user = new User();
        user.setUsername("user_service_user2");
        user.setPassword("pwd");

        //when
        userService.saveUser(user);
        User savedUser = userService.findByUserName("user_service_user2");

        //then
        assertEquals("user_service_user2", savedUser.getUsername());
    }
}
