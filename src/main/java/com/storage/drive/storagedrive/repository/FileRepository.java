package com.storage.drive.storagedrive.repository;

import com.storage.drive.storagedrive.model.File;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileRepository {

    @Select("SELECT * FROM FILE")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILE WHERE fileId = #{fileId}")
    File getFileById(Long fileId);

    @Select("SELECT * FROM FILE WHERE userId = #{userId}")
    List<File> getFilesOfUser(Long userId);

    @Select("SELECT * FROM FILE WHERE fileName = #{fileName}")
    File getFileByFileName(String fileName);

    @Insert("INSERT INTO FILE (fileName, contentType, fileSize, fileData, fileUploadDateTime, userId) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{fileUploadDateTime}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int addFile(File file);

    @Update("UPDATE FILE SET fileName = #{fileName}, contentType = #{contentType}, fileSize = #{fileSize}, fileData = #{fileData}, userId = #{userId} WHERE fileId=#{fileId}")
    int updateFile(File file, Long fileId);

    @Delete("DELETE FROM FILE WHERE fileId = #{fileId}")
    int deleteFileById(Long fileId);
}
