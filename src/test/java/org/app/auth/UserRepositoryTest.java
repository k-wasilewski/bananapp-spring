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
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    private User user;

    @Before
    public void init() {
        user = new User();
        user.setUsername("user1");
        if (userRepository.findByUsername("user1")==null) {
            userService.saveUser(user);
        }
    }

    @Test
    public void findByUsername() {
        assertEquals(user.getUsername(),
                userRepository.findByUsername("user1").getUsername());
    }
}
