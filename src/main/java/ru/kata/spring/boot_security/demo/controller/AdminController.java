package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getAllUsers(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user", userService.findUserByName(auth.getName()));
        model.addAttribute("newuser", new User());
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "admin";
    }

    @GetMapping(value = "/edit")
    public String getPageEdit(ModelMap model, @RequestParam Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("listRoles", listRoles);
        return "edit";
    }

    @PostMapping("/edit")
    public String updateUser(@ModelAttribute User model,
                             @RequestParam(value = "action") String action) {
        userService.saveUser(model);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String getPageDelete(ModelMap model, @RequestParam Long id) {
        model.addAttribute("user", userService.findUserById(id));
        return "delete";
    }

    @PostMapping(value = "delete")
    public String Delete(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/add")
    public String getPageNewUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", userService.listRoles());
        return "adminadd";
    }

    @PostMapping(value = "/adminadd")
    public String SaveNewUser(@ModelAttribute User model) {
        userService.saveUser(model);
        return "redirect:/admin";
    }
}