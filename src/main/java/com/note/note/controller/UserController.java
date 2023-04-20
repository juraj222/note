package com.note.note.controller;

import com.note.note.model.Note;
import com.note.note.model.NoteUser;
import com.note.note.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;

@Controller
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("noteUser", new NoteUser());

        return "index";
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public NoteUser getUser(@PathVariable Long id) throws Exception {
        return userService.getUser(id);
    }

    @GetMapping("/users/")
    public String getOrCreateUser(@ModelAttribute("noteUser") NoteUser noteUser) throws Exception {
        NoteUser user;
        try {
            user = userService.getUserByName(noteUser.getName());
        } catch (Exception e) {
            user = userService.createUser(noteUser.getName());
        }
        return "redirect:/notes/" + user.getId();
    }

    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
