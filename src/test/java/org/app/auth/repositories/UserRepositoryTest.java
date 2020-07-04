package org.app.auth.repositories;

import org.app.auth.entities.User;
import org.app.auth.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    private User user;

    @Before
    public void init() {
        //given
        user = new User();
        user.setUsername("user_repo_user");
        user.setPassword("abc");

        if (userRepository.findByUsername("user_repo_user")==null) {
            userService.saveUser(user);
        }
    }

    @Test
    public void findByUsername() {
        //when
        User savedUser = userRepository.findByUsername("user_repo_user");

        //then
        assertEquals(user.getUsername(), savedUser.getUsername());
    }
}
