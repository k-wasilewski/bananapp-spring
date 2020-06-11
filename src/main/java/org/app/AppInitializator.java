package org.app;

import org.app.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppInitializator {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @PostConstruct
    private void init() {
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);
    }
}