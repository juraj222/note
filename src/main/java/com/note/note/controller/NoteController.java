package com.note.note.controller;

import com.note.note.model.Note;
import com.note.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NoteController {
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes/{userId}")
    public String notes(Model model, @PathVariable Long userId) throws Exception {
        model.addAttribute("notes", noteService.getNote(userId));
        return "note_page";
    }

    @PostMapping("/notes/")
    @ResponseBody
    public Note createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }


    @PutMapping("/notes/{id}")
    @ResponseBody
    public Note updateNote(@PathVariable Long id, @RequestBody Note note) throws Exception {
        return noteService.updateNote(id, note);
    }

    @DeleteMapping("/notes/{id}")
    public void delete(@PathVariable Long id) {
        noteService.deleteNote(id);
    }
}
