package org.app;

import org.app.auth.entities.Role;
import org.app.auth.entities.User;
import org.app.auth.repositories.RoleRepository;
import org.app.auth.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppInitializer {
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