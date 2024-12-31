package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AppServiceImpl;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationRestController {
    private final AppServiceImpl appService;

    @Autowired
    public ApplicationRestController(AppServiceImpl appService) {
        this.appService = appService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(appService.findAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(appService.getOneUser(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> insert(@Valid @RequestBody User user, BindingResult bindingResult) {
        return ResponseEntity.ok(appService.insertUser(user, bindingResult));
    }

    @PutMapping("/users")
    public ResponseEntity<User> update(@Valid @RequestBody User user, BindingResult bindingResult) {
        return ResponseEntity.ok(appService.updateUser(user, bindingResult));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/roles")
    public ResponseEntity<Iterable<Role>> findAllRoles() {
        return ResponseEntity.ok(appService.findAllRoles());
    }
}
