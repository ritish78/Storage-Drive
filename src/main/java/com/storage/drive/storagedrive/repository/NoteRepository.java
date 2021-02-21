package com.storage.drive.storagedrive.repository;

import com.storage.drive.storagedrive.model.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NoteRepository {

    @Select("SELECT * FROM NOTE")
    List<Note> findAll();

    @Select("SELECT * FROM NOTE WHERE noteId = #{noteId}")
    Note findNoteById(Long noteId);

    @Select("SELECT * FROM NOTE WHERE userId = #{userId}")
    List<Note> findNoteOfUserById(Long userId);

    @Insert("INSERT INTO NOTE (noteTitle, noteDescription, userId) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int addNote(Note note);


//    @Update("UPDATE NOTE SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
//    int updateNoteByNoteId(Note note);

    @Update("UPDATE NOTE SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
    int updateNote(Note note);

    @Delete("DELETE FROM NOTE WHERE noteId = #{noteId}")
    int deleteNoteById(Long noteId);
}
