package com.storage.drive.storagedrive.services;

import com.storage.drive.storagedrive.model.Users;
import com.storage.drive.storagedrive.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private UserRepository userRepository;
    private HashService hashService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public AuthenticationService(UserRepository userRepository, HashService hashService) {
        this.userRepository = userRepository;
        this.hashService = hashService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<Users> user = Optional.ofNullable(userRepository.findByUsername(username));

        if (user.isPresent()) {
            String encodedSalt = user.get().getSalt();
            String hashedPassword = hashService.getHashedValue(password, encodedSalt);

            if (user.get().getPassword().equals(hashedPassword)) {
                return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
            }
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
