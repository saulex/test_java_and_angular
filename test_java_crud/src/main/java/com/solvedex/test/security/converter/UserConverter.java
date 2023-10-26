package com.solvedex.test.security.converter;

import com.solvedex.test.security.model.dto.NewUser;
import com.solvedex.test.security.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User convertNewUserToUser(NewUser newUser) {
        return new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(), newUser.getPassword());

    }
}
