package com.calife.financeiro.web.controller;

import com.calife.financeiro.domain.User;
import com.calife.financeiro.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        User user = userService.findByEmail("emircalife@gmail.com");
        if (user == null) {
            User userAdm = new User();
            userAdm.setUsername("emircalife");
            userAdm.setEmail("emircalife@gmail.com");
            userAdm.setPassword("123");
            userAdm.setRepeatPassword(userAdm.getPassword());
            userService.salvar(userAdm);
        }

        return "login";
    }
}
