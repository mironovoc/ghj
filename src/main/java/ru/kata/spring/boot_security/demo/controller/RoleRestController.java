package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleRestController {

    private final UserServiceImp userService;

    @Autowired
    public RoleRestController(UserServiceImp userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return userService.listRoles();
    }
}