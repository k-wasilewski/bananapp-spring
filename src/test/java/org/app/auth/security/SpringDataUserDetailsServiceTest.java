package org.app.auth.security;

import org.app.auth.entities.User;
import org.app.auth.repositories.UserRepository;
import org.app.auth.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataUserDetailsServiceTest {
    @Autowired
    SpringDataUserDetailsService service;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Before
    public void createUser() {
        User user = new User();
        user.setUsername("test@test.pl");
        user.setPassword("test");
        userService.saveUser(user);
    }

    @After
    public void deleteUser() {
        userRepository.delete(userRepository.findByUsername("test@test.pl"));
    }

    @Test
    @Transactional
    public void loadUserByUsername() {
        final String username = "test@test.pl";
        final User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));

        assertEquals(new org.springframework.security.core.userdetails.User(
                        user.getUsername(), user.getPassword(), grantedAuthorities),
                service.loadUserByUsername(username));
    }
}
