package com.storage.drive.storagedrive.controller;

import com.storage.drive.storagedrive.model.Note;
import com.storage.drive.storagedrive.services.NoteService;
import com.storage.drive.storagedrive.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping(path = "/note/new")
    public String saveNote(Note note, Model model) {
        Long currentUserId = userService.getCurrentUserId();

        if (currentUserId != null && note != null && note.getNoteId() != null) {
            int noOfAddedNote = noteService.updateNoteDetails(note, currentUserId);

            if (noOfAddedNote == 1) {
                model.addAttribute("result", "success");
            }
        } else if (currentUserId != null && note != null && note.getNoteId() == null) {
            int noOfAddedNote = noteService.addNoteByUserId(note, currentUserId);

            if (noOfAddedNote == 1) {
                model.addAttribute("result", "success");
            }
        }
        return "result";
    }

    @GetMapping(path = "/note/delete")
    public String deleteNote(@ModelAttribute("note") Note note, @RequestParam(required = false, name = "noteId") Long noteId,
                                    Model model) {

        Long currentUserId = userService.getCurrentUserId();

        //Checking to see if the note to be deleted is under the current user
        //First, getting the note to be deleted
        Note noteByNoteId = noteService.getNoteByNoteId(noteId);
        //Then, if the note is NOT under the current user, then we don't delete the note
        if (noteByNoteId.getUserId() != currentUserId) {
            model.addAttribute("result", "error");
            return "result";
        }


        if (currentUserId != null && noteId != null) {
            int noOfDeletedNote = noteService.deleteNoteById(noteId);

            if (noOfDeletedNote == 1) {
                model.addAttribute("result", "success");
            }
        }
        return "result";
    }
}
