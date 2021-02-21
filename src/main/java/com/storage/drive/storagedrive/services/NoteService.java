package com.storage.drive.storagedrive.services;

import com.storage.drive.storagedrive.model.Note;
import com.storage.drive.storagedrive.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Note getNoteByNoteId(Long noteId) {
        return noteRepository.findNoteById(noteId);
    }

    public List<Note> getAllNoteByUsedId(Long userId) {
        return noteRepository.findNoteOfUserById(userId);
    }

    public int addNoteByUserId(Note note, Long userId) {
        if (userId != null) {
            note.setUserId(userId);
            int addNote = noteRepository.addNote(note);
            return addNote;
        } else {
            return 0;
        }
    }

    /**
     *  Not using this method. It might be implemented.
     *
    public int updateNoteDetailsByDetails(String noteTitle, String noteDescription, Long noteId) {
        Optional<Note> optionalNote = Optional.ofNullable(noteRepository.findNoteById(noteId));

        if (optionalNote.isPresent()) {
            int updatedNoteCount = noteRepository.updateNoteByNoteId(noteTitle, noteDescription, noteId);
            return updatedNoteCount;
        } else {
            logger.warn("Tried to update note which doesn't exists. Note ID: " + noteId);
            //No note is updated, hence we are sending back 0.
            return 0;
        }
    }

     */

    public int updateNoteDetails(Note note, Long userId) {
        if (userId != null) {
            return noteRepository.updateNote(note);
        } else {
            return 0;
        }
    }

    public int deleteNoteById(Long noteId) {
        Optional<Note> optionalNote = Optional.ofNullable(noteRepository.findNoteById(noteId));

        if (optionalNote.isPresent()) {
            int deletedNoteCount = noteRepository.deleteNoteById(noteId);
            return deletedNoteCount;
        } else {
            logger.warn("Tried to delete note which doesn't exists. Note ID: " + noteId);
            return 0;
        }
    }





}
