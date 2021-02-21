package com.storage.drive.storagedrive.services;

import com.storage.drive.storagedrive.model.Users;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    private UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public boolean signUpUser(Users users) {
        /**
         * We are using Username to check whether the username is in the
         * database or not. We can do the same for email.
         */
        String username = users.getUsername();

        if (userService.isUsernameAvailable(username)) {
            userService.addUser(users);
            return true;
        } else {
            return false;
        }
    }
}
