package org.app.auth;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}
