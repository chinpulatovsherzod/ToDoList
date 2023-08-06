package com.example.todolist.controller;


import com.example.todolist.exception.UserNotFoundException;
import com.example.todolist.model.User;
import com.example.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = userService.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle" , "Add New User");
        return "userForm";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes attributes){
        userService.save(user);
        attributes.addAttribute("message","The user has been saved successfully.");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes attributes){
        try {
           User user = userService.get(id);
           model.addAttribute("user", user);
           model.addAttribute("pageTitle" , "Edit User (ID: " + id + ")");
           return "userForm";
        } catch (UserNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
            return "redirect:/users";
        }

    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes attributes){
        try {
            userService.delete(id);
            attributes.addFlashAttribute("message", "The user ID" + id + "has been deleted");
        } catch (UserNotFoundException e) {
            attributes.addAttribute("message", e.getMessage());
        }
        return "redirect:/users";

    }



}
