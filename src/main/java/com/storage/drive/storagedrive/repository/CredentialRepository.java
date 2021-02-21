package com.storage.drive.storagedrive.repository;

import com.storage.drive.storagedrive.model.Credential;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CredentialRepository {

    @Select("SELECT * FROM CREDENTIAL")
    List<Credential> findAll();

    @Select("SELECT * FROM CREDENTIAL WHERE credentialId = #{credentialId}")
    Credential findCredentialById(Long credentialId);

    @Select("SELECT * FROM CREDENTIAL WHERE userId = #{userId}")
    List<Credential> findCredentialByUserId(Long userId);

    /**
     * We might need to change addCredential parameters to just Credential instead of Credential and userId
     * UPDATE: Changed!
     */

    @Insert("INSERT INTO CREDENTIAL (url, username, password, key, userId) VALUES (#{url}, #{username}, #{password}, #{key}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int addCredential(Credential credential);

    @Update("UPDATE CREDENTIAL SET url = #{url}, username = #{username}, password = #{password}, key = #{key} WHERE credentialId = #{credentialId}")
    int updateCredentialById(Credential credential, Long credentialId);

    @Update("UPDATE CREDENTIAL SET url = #{url}, username = #{username}, password = #{password}, key = #{key} WHERE credentialId = #{credentialId}")
    int updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIAL WHERE credentialId = #{credentialId}")
    int deleteCredentialById(Long credentialId);

    @Delete("DELETE FROM CREDENTIAL WHERE userId = #{userId}")
    int deleteCredentialByUserId(Long userId);

}
