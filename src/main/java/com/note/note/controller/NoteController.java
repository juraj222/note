package com.note.note.controller;

import com.note.note.model.Note;
import com.note.note.model.NoteUser;
import com.note.note.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NoteController {
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @RequestMapping("/notes/{userId}")
    public String notes(Model model, @PathVariable Long userId) throws Exception {
        model.addAttribute("notes", noteService.getNote(userId));
        model.addAttribute("note", new Note());
        model.addAttribute("userId", userId);
        return "note_page";
    }

    @PostMapping("/notes/")
    public String createNote(@ModelAttribute("note") Note note, @RequestParam("userId") Long userId) {
        noteService.createNote(note, userId);
        return "redirect:/notes/" + userId;
    }


    @PutMapping("/notes/{userId}/{id}")
    public String updateNote(@PathVariable Long userId, @PathVariable Long id, @ModelAttribute("note") Note note) throws Exception {
        note.setUser(new NoteUser(userId));
        noteService.updateNote(id, note);
        return "redirect:/notes/" + userId;
    }

    @DeleteMapping("/notes/{userId}/{id}")
    public String delete(@PathVariable Long userId, @PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes/" + userId;
    }
}
