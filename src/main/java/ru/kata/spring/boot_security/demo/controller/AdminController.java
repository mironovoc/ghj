package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String us = auth.getName();
        model.addAttribute("user", userService.findUserByName(us));
        model.addAttribute("newuser", new User());
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "admin";
    }

    @GetMapping(value = "/edit")
    public String edit(ModelMap model, @RequestParam Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute User model,
                       @RequestParam(value = "action") String action) {
        if ("save".equals(action)) {
            userService.saveUser(model);
        } else if ("cancel".equals(action)) {
            // do stuff
        } else if ("newthing".equals(action)) {
            // do stuff
        } else {
            // do stuff
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String del(ModelMap model, @RequestParam Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "delete";
    }

    @PostMapping(value = "delete")
    public String deleteItem(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", userService.listRoles());
        return "adminadd";
    }

    @PostMapping(value = "/adminadd")
    public String addNewOrder(@ModelAttribute User model) {
        userService.saveUser(model);
        return "redirect:/admin";
    }
}