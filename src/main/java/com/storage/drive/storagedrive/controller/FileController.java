package com.storage.drive.storagedrive.controller;

import com.storage.drive.storagedrive.model.File;
import com.storage.drive.storagedrive.services.FileService;
import com.storage.drive.storagedrive.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Controller
public class FileController {

    private UserService userService;
    private FileService fileService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping(path = "/file/upload")
    public String saveFileOfUser(@RequestParam("fileUpload") MultipartFile multipartFile, File file, Model model,
                                 Authentication authentication) {
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
            try {
                if (file.getFileId() == null) {
                    //Checking if the file with the same name exists in the server or not
                    File fileByFileName = fileService.getFileByFileName(multipartFile.getOriginalFilename());

                    //If a file with same name exists, then we are throwing error
                    if (fileByFileName != null) {
                        model.addAttribute("result", "error");
                        model.addAttribute("message", "File with same name exists!");
                        return "result";
                    }

                    boolean fileUploadedSuccessfully = fileService.addFileByUserId(multipartFile, currentUserId);

                    logger.info("Uploaded: " + fileUploadedSuccessfully);

                    if (fileUploadedSuccessfully) {
                        logger.info("Added File to the DB for User ID: " + currentUserId);
                        model.addAttribute("result", "success");
                    } else {
                        logger.error("Could not add file to the DB for User ID: " + currentUserId);
                        model.addAttribute("result", "error");
                        model.addAttribute("message", "Please check if the provided file is empty or not and also the file size!");
                    }

                }
            } catch (Exception e) {
                logger.error("Exception occurred while adding File for User ID: " + currentUserId);
                model.addAttribute("result", "error");
                model.addAttribute("message", "Please check if file is empty or not and also check file size!");
            }
        }

        return "result";
    }



    @GetMapping("/file/delete")
    public String deleteFile(@RequestParam(required = false, name = "fileId") Long fileId, Model model) {
        Long currentUserId = userService.getCurrentUserId();

        //Checking to see if the file to be deleted is under the current user
        //First, getting the file to be deleted
        File fileByFileId = fileService.getFileByFileId(fileId);
        //Then, if the file is NOT under the current user, then we don't delete the file
        if (fileByFileId.getUserId() != currentUserId) {
            model.addAttribute("result", "error");
            return "result";
        }

        if (currentUserId != null && fileId != null) {
            int noOfDeletedFile = fileService.deleteFileByFileId(fileId);

            if (noOfDeletedFile == 1) {
                model.addAttribute("result", "success");
            } else {
                model.addAttribute("result", "error");
            }
        }

        return "result";
    }


    @GetMapping("/file/download")
    public ResponseEntity<InputStreamResource> downloadFile(@RequestParam(required = false, name= "fileId") Long fileId, Model model) {
        Long currentUserId = userService.getCurrentUserId();

        File file = fileService.getFileByFileId(fileId);

        //String fileName = file.getFileName();
        String contentType = file.getContentType();
        byte[] fileData = file.getFileData();

        InputStream inputStream = new ByteArrayInputStream(fileData);
        InputStreamResource resource = new InputStreamResource(inputStream);


        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + file.getFileName())
                .body(resource);
    }
}
