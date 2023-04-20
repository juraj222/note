package com.note.note.repository;

import com.note.note.model.NoteUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<NoteUser, Long> {
    Optional<NoteUser> findUserByName(String name);
}
