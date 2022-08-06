package com.example.demo.controller;

import com.example.demo.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminController {
    private final UserService userService;
    private final RolesService rolesService;

    @Autowired
    public AdminController(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }


// LIST ALL USERS
    @GetMapping({"/index","/"})
    public String readAll(Model model, Principal principal) {
        model.addAttribute("users", userService.readAll());
        model.addAttribute("principal", userService.findUserByEmail(principal.getName()));
        model.addAttribute("roles", rolesService.getAllRoles());
        model.addAttribute("newUser", new Users());
        return "index";
    }

//CREATE
    @PostMapping("/new")
    public String create(@ModelAttribute("newUser") Users user,
                         @RequestParam(value = "roles", required = false) String [] roles,
                         @ModelAttribute("password") String password) {
        userService.create(user, roles, password);
        return "redirect:/";
    }

//EDIT
    @PutMapping("/index/edit/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("newUser") Users user,
                         @RequestParam(value = "roles", required = false) String [] roles,
                         @ModelAttribute("password") String password) {
        userService.update(id, user, roles, password);
        return "redirect:/";
    }

// DELETE
    @DeleteMapping("/index/delete/{id}")
    public String delete(@ModelAttribute("newUser") Users user) {
        userService.delete(user.getId());
        return "redirect:/";
    }

}

