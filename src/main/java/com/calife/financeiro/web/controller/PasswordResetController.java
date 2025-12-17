package com.calife.financeiro.web.controller;

import com.calife.financeiro.service.PasswordResetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @GetMapping("/esqueci-senha")
    public String esqueciSenha() {
        return "esqueci-senha";
    }

    @PostMapping("/esqueci-senha")
    public String solicitarReset(@RequestParam String email, Model model) {
        passwordResetService.requestReset(email);
        model.addAttribute("msg", "Se o e-mail existir, enviaremos um link de redefinição.");
        return "redirect:/login";
    }

    @GetMapping("/resetar-senha")
    public String resetarSenha(@RequestParam String token, Model model) {
        boolean ok = passwordResetService.isTokenValid(token);
        model.addAttribute("token", token);
        model.addAttribute("tokenValido", ok);
        return "resetar-senha";
    }

    @PostMapping("/resetar-senha")
    public String salvarNovaSenha(
            @RequestParam String token,
            @RequestParam String senha,
            @RequestParam String confirmarSenha,
            Model model
    ) {
        if (!senha.equals(confirmarSenha)) {
            model.addAttribute("token", token);
            model.addAttribute("tokenValido", true);
            model.addAttribute("erro", "As senhas não conferem.");
            return "resetar-senha";
        }

        passwordResetService.resetPassword(token, senha);
        return "redirect:/login?reset=ok";
    }
}
