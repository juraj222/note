package com.note.note.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class NoteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    @OneToMany
    List<Note> notes = new ArrayList<>();

    public NoteUser(String name) {
        this.name = name;
    }

    public NoteUser() {
    }

    public NoteUser(Long id) {
        this.id = id;
    }
}
