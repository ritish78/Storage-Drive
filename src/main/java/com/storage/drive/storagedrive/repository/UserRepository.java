package com.storage.drive.storagedrive.repository;

import com.storage.drive.storagedrive.model.Users;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {

    @Select("SELECT * FROM USERS")
    List<Users> findAll();

    @Select("SELECT * FROM USERS WHERE userId = #{userId}")
    Users findById(Long userId);

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    Users findByUsername(String username);

    @Insert("INSERT INTO USERS (username, salt, password, firstName, lastName) values (#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int addUser(Users users);

    @Update("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, firstName = #{firstName}, lastName = #{lastName}")
    int update(Users users);

    @Delete("DELETE FROM USERS WHERE username = #{username}")
    int deleteUserByUsername(String username);

    @Select("SELECT username FROM USERS WHERE userId = #{userId}")
    String findUsernameById(Long userId);
}
