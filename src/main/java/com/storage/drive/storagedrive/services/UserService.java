package com.storage.drive.storagedrive.services;

import com.storage.drive.storagedrive.model.Users;
import com.storage.drive.storagedrive.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final HashService hashService;

    @Autowired
    public UserService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
    }

    public boolean isUsernameAvailable(String username) {
        return userRepository.findByUsername(username) == null;
    }

    public int addUser(Users users) {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];

        random.nextBytes(salt);

        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(users.getPassword(), encodedSalt);

        Users newUsers = new Users(null, users.getUsername(), encodedSalt, hashedPassword, users.getFirstName(), users.getLastName());

        int noOfChange = userRepository.addUser(newUsers);

        return noOfChange;
    }

    public Users getUser(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * To get the current user Id.
     * Principal is the current logged in user,
     * @return UserId or null if not logged in.
     */
    public Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        if (username != null) {
            Users users = this.getUser(username);

            if (users != null) {
                return users.getUserId();
            }
        }
        return null;
    }


}
