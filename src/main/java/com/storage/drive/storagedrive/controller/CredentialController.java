package com.storage.drive.storagedrive.controller;

import com.storage.drive.storagedrive.model.Credential;
import com.storage.drive.storagedrive.model.Users;
import com.storage.drive.storagedrive.services.CredentialService;
import com.storage.drive.storagedrive.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }


    @PostMapping(path = "/credential/new")
    public String addCredential(Credential credential, Model model) {
        Long currentUserId = userService.getCurrentUserId();

        if (credential != null && currentUserId != null) {
            if (credential.getCredentialId() != null) {
                int noOfUpdatedCredential = credentialService.updateCredential(credential);

                if (noOfUpdatedCredential != 0) {
                    model.addAttribute("result", "success");
                }
            } else if (credentialService.addCredentialByUserId(credential, currentUserId) == 1) {
                model.addAttribute("result", "success");
            }
            return "result";
        }
        return "redirect:/login";
    }


    @GetMapping(path = "/credential/delete")
    public String deleteCredential(@ModelAttribute("credential") Credential credential, @RequestParam(required = false, name = "credentialId") Long credentialId,
                                   Model model) {
        Long currentUserId = userService.getCurrentUserId();

        //Checking to see if to be deleted credential belongs to the current user
        //First, getting the credential to be deleted
        Credential credentialById = credentialService.getCredentialById(credentialId);
        //Then, if the credential is NOT under the current user, then we don't delete the credential
        if (credentialById.getUserId() != currentUserId) {
            model.addAttribute("result", "error");
            return "result";
        }


        if (currentUserId != null && credentialId != null) {
            int noOfDeletedCredential = credentialService.deleteCredentialById(credentialId);

            if (noOfDeletedCredential != 0) {
                model.addAttribute("result", "success");
            }
        }
        return "result";
    }

}
