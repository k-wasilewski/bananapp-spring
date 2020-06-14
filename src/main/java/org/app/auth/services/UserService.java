package org.app.auth.services;

import org.app.auth.entities.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
}
