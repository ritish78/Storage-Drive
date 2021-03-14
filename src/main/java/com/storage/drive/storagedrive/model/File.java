package com.storage.drive.storagedrive.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;


public class File {

    private Long fileId;
    private String fileName;
    private String contentType;
    private Long fileSize;
    private byte[] fileData;
    private LocalDateTime fileUploadDateTime;
    private Long userId;

    public static final String DATE_TIME_FORMATTER = "yyyy-MM-dd";

    public File() {
    }

    public File(Long fileId, String fileName, String contentType, Long fileSize, byte[] fileData, Long userId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.userId = userId;
        fileUploadDateTime = fileUploadDateTime.now();
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getFileUploadDateTime() {
        return fileUploadDateTime;
    }

    public String getFileUploadDateInString() {
        return this.fileUploadDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER));
    }

    public void setFileUploadDateTime(LocalDateTime fileUploadDateTime) {
        this.fileUploadDateTime = fileUploadDateTime;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", contentType='" + contentType + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileData=" + Arrays.toString(fileData) +
                ", userId=" + userId +
                '}';
    }
}
