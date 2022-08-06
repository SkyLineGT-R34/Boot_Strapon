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


//// NEW USER
//    @GetMapping("/index/new")
//    public String newUser(Model model, @ModelAttribute("user") Users user) {
//        model.addAttribute("user", new Users());
//        model.addAttribute("roles", rolesService.getAllRoles());
//        return "new";
//    }
    @PostMapping("/new")
    public String create(@ModelAttribute("newUser") Users user,
                         @RequestParam(value = "roles", required = false) String [] roles,
                         @ModelAttribute("password") String password) {
        userService.create(user, roles, password);
        return "redirect:/";
    }


//// EDIT
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") long id) {
//        model.addAttribute("user", userService.read(id));
//        model.addAttribute("roles", rolesService.getAllRoles());
//        return "edit";
//    }
    @PutMapping("/index/edit/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("newUser") Users user,
                         @RequestParam(value = "roles", required = false) String [] roles,
                         @ModelAttribute("password") String password) {
        userService.update(id, user, roles, password);
        return "redirect:/";
    }


// DELETE //
    @DeleteMapping("/index/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/";
    }

//    @GetMapping("/user")
//    public String user(Model model, Principal principal) {
//        model.addAttribute("principal", userService.findUserByEmail(principal.getName()));
//        model.addAttribute("roles", rolesService.getAllRoles());
//        return "index";
//    }
}

