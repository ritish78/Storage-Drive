package com.storage.drive.storagedrive.services;

import com.storage.drive.storagedrive.model.Credential;
import com.storage.drive.storagedrive.repository.CredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class CredentialService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CredentialRepository credentialRepository;
    private final EncryptionService encryptionService;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository, EncryptionService encryptionService) {
        this.credentialRepository = credentialRepository;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getAllCredentials() {
        return credentialRepository.findAll();
    }

    public Credential getCredentialById(Long credentialId) {
        return credentialRepository.findCredentialById(credentialId);
    }

    public List<Credential> getCredentialsByUserId(Long userId) {
        List<Credential> listOfCredential =  credentialRepository.findCredentialByUserId(userId);

        for (Credential credential : listOfCredential) {
            String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
            credential.setDecryptedPassword(decryptedPassword);
        }
        return listOfCredential;
    }

    public int addCredentialByUserId(Credential credential, Long userId) {
        if (userId != null) {
            credential.setUserId(userId);
            hashPassword(credential);

            int credentialCount = credentialRepository.addCredential(credential);
            return credentialCount;
        } else {
            //Returning 0 if we don't add user to the repository
            return 0;
        }
    }

    public int updateCredentialById(Credential credential, Long credentialId) {
        Optional<Credential> optionalCredential = Optional.ofNullable(credentialRepository.findCredentialById(credentialId));

        if (optionalCredential.isPresent()) {
            hashPassword(credential);
            int credentialCount = credentialRepository.updateCredentialById(credential, credentialId);
            return credentialCount;
        } else {
            logger.warn("Tried to update credential which doesn't exist! Credential ID: " + credentialId);
            return 0;
        }
    }

    public int updateCredential(Credential credential) {
        hashPassword(credential);
        int credentialCount = credentialRepository.updateCredential(credential);
        return credentialCount;
    }

    public int deleteCredentialById(Long credentialId) {
        Optional<Credential> optionalCredential = Optional.ofNullable(credentialRepository.findCredentialById(credentialId));

        if (optionalCredential.isPresent()) {
            int credentialCount = credentialRepository.deleteCredentialById(credentialId);
            return credentialCount;
        } else {
            logger.warn("Tried to delete credential which doesn't exist! Credential ID: " + credentialId);
            return 0;
        }
    }

    public int deleteCredentialByUserId(Long userId) {
        List<Credential> credentialByUserId = credentialRepository.findCredentialByUserId(userId);

        if (credentialByUserId != null) {
            int credentialCount = credentialRepository.deleteCredentialByUserId(userId);
            return credentialCount;
        } else {
            logger.warn("Credential for the user is null for user id: " + userId);
            return 0;
        }
    }

    private void hashPassword(Credential credential) {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];

        random.nextBytes(salt);

        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        credential.setKey(encodedSalt);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));
    }


}
