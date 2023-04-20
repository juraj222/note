package com.note.note.service;

import com.note.note.model.Note;
import com.note.note.model.NoteUser;
import com.note.note.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {
    Logger logger = LoggerFactory.getLogger(NoteService.class);
    private NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note, Long userId) {
        logger.info("createNote " + userId + note.toString());
        note.setUser(new NoteUser(userId));
        return noteRepository.save(note);
    }

    public List<Note> getNote(Long userId) throws Exception {
        logger.info("getNote " + userId);
        List<Note> notes = noteRepository.findByUserId(userId);
        logger.info("getNote notes " + notes.stream().map(Object::toString).collect(Collectors.joining(",")));
        return notes;
    }

    public Note updateNote(Long id, Note note) throws Exception {
        logger.info("updateNote " + id + " " + note.toString());
        note.setId(id);
        Optional<Note> byId = noteRepository.findById(id);
        Note toUpdate = byId.orElseThrow(() -> new Exception("note not found"));
        toUpdate.setText(note.getText());
        return noteRepository.save(toUpdate);
    }

    public void deleteNote(Long id) {
        logger.info("deleteNote " + id);
        noteRepository.deleteById(id);
    }
}
