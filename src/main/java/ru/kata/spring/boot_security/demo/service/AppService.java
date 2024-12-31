package ru.kata.spring.boot_security.demo.service;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import javax.servlet.http.HttpSession;
import java.util.List;
//CRUD operations
public interface AppService extends UserDetailsService {
    List<User> findAllUsers();

    User getOneUser(Long id);

    User insertUser(User user, BindingResult bindingResult);

    User updateUser(User user, BindingResult bindingResult);

    void deleteUser(Long id);

    Iterable<Role> findAllRoles();

    String getPage(Model model, HttpSession session, @Nullable Authentication auth);
}
