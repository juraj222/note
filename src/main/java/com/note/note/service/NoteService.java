package com.note.note.service;

import com.note.note.model.Note;
import com.note.note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> getNote(Long userId) throws Exception {
        List<Note> notes = noteRepository.findByUserId(userId);
        return notes;
    }

    public Note updateNote(Long id, Note note) throws Exception {
        note.setId(id);
        Optional<Note> byId = noteRepository.findById(id);
        Note toUpdate = byId.orElseThrow(() -> new Exception("note not found"));
        return noteRepository.save(toUpdate);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
