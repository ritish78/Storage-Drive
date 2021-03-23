package com.storage.drive.storagedrive.controller;

import com.storage.drive.storagedrive.model.Credential;
import com.storage.drive.storagedrive.model.Note;
import com.storage.drive.storagedrive.model.Users;
import com.storage.drive.storagedrive.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private AuthorizationService authorizationService;
    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;

    public UserController(AuthorizationService authorizationService, UserService userService, FileService fileService,
                          NoteService noteService, CredentialService credentialService) {
        this.authorizationService = authorizationService;
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping(path = "/")
    public String getIndexPage() {
        return "redirect:/home";
    }

    @GetMapping(path = "/home")
    public String getHomePage(@ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential,
                              Authentication authentication, Model model) {
        //Principal is the currently signed in user.
        String username = (String) authentication.getPrincipal();

        //Getting the current user ID
        Long currentUserId = userService.getCurrentUserId();

        /**
         * We can user the username or currentUserId to get the details of the current user.
         * Either of them returns the same user. Only one of the above fields is required.
         * Both of them are implemented to see how we can get same user from username and id.
         *
         * Here, we have @param Authentication, which we don't need if we are going through
         * route of getting currentUserId
         */

        if (currentUserId != null) {
            Map<String, Object> userData = new HashMap<>();

            userData.put("noteList", this.noteService.getAllNoteByUsedId(currentUserId));
            userData.put("credentialList", this.credentialService.getCredentialsByUserId(currentUserId));
            userData.put("fileList", this.fileService.getFilesOfUser(currentUserId));
            userData.put("username", this.userService.getCurrentUsername());

            model.addAllAttributes(userData);

            return "home";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }


    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpUser(@ModelAttribute("users") Users users, Model model) {
        String signUpError = null;

        if (!userService.isUsernameAvailable(users.getUsername())) {
            signUpError = "Sorry, Username is already taken!";
            logger.warn(String.format("Username %s, is already taken", users.getUsername()));
        }

        if (signUpError == null) {
            int userAdded = userService.addUser(users);
            if (userAdded != 1) {
                signUpError = "Error in signing up. Please try again";
                logger.warn("Couldn't sign up for user: " + users.getUsername());
            }
        }

        if (signUpError == null) {
            model.addAttribute("signUpSuccess", true);
            return "login";
        } else {
            model.addAttribute("signUpError", true);
        }

        return "signup";
    }
}
