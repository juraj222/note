package com.note.note.service;

import com.note.note.model.NoteUser;
import com.note.note.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public NoteUser createUser (NoteUser noteUser) {
        return userRepository.save(noteUser);
    }

    public NoteUser createUser (String name) {
        return userRepository.save(new NoteUser(name));
    }

    public NoteUser getUser(Long id) throws Exception {
        Optional<NoteUser> byId = userRepository.findById(id);
        return byId.orElseThrow(() -> new Exception("user not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public NoteUser getUserByName(String name) throws Exception {
        Optional<NoteUser> byId = userRepository.findUserByName(name);
        return byId.orElseThrow(() -> new Exception("user not found"));
    }
}
