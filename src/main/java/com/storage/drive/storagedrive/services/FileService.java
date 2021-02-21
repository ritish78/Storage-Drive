package com.storage.drive.storagedrive.services;

import com.storage.drive.storagedrive.model.File;
import com.storage.drive.storagedrive.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getAllFiles() {
        return fileRepository.getAllFiles();
    }

    public File getFileByFileId(Long fileId) {
        return fileRepository.getFileById(fileId);
    }

    public List<File> getFilesOfUser(Long userId) {
        return fileRepository.getFilesOfUser(userId);
    }

    public File getFileByFileName(String fileName) {
        return fileRepository.getFileByFileName(fileName);
    }

    public int addFile(File file) {
        int fileCount =  fileRepository.addFile(file);
        return fileCount;
    }

    public boolean addFileByUserId(MultipartFile multipartFile, Long userId) {
        try {
            if (multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().isEmpty()) {
                return false;
            }

            /*Creating a new object of File and assigning the values
              that we get from uploading file and then saving it.
             */
            File newFile = new File();
            newFile.setFileName(multipartFile.getOriginalFilename());
            newFile.setContentType(multipartFile.getContentType());
            newFile.setFileData(multipartFile.getBytes());
            newFile.setFileSize(multipartFile.getSize());
            newFile.setUserId(userId);

            int noOfAddedFile = fileRepository.addFile(newFile);
            logger.info(String.format("Adding %d file for User ID: %d", noOfAddedFile, userId));
        } catch (IOException e) {
            logger.warn(e.getMessage());
            return false;
        }

        //If file upload is successful.
        return true;
    }

    public int updateFile(File file, Long fileId) {
        Optional<File> optionalFile = Optional.ofNullable(fileRepository.getFileById(fileId));

        if (optionalFile.isPresent()) {
            int updateFileCount = fileRepository.updateFile(file, fileId);
            return updateFileCount;
        } else {
            logger.warn("Tried to update File which doesn't exists! File ID: " + fileId);
            return 0;
        }
    }

    public int deleteFileByFileId(Long fileId) {
        Optional<File> optionalFile = Optional.ofNullable(fileRepository.getFileById(fileId));

        if (optionalFile.isPresent()) {
            int fileCount = fileRepository.deleteFileById(fileId);
            return fileCount;
        } else {
            logger.warn("Tried to delete File which doesn't exists! File ID: " + fileId);
            return 0;
        }
    }

}
