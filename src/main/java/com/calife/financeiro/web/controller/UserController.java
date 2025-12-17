package com.calife.financeiro.web.controller;

import com.calife.financeiro.domain.User;
import com.calife.financeiro.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ... other methods ...
    @GetMapping("/editar/{id}")
    public String editar(ModelMap model, @PathVariable Long id, HttpServletRequest request) {
        model.addAttribute("user", service.buscarPorId(id));
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/usuario/cadastro :: corpo";
        }
        return "/usuario/cadastro";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/usuario/cadastro :: corpo";
        }
        return "/usuario/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model, HttpServletRequest request,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("usuarios", service.buscarTodos(pageable));
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/usuario/lista :: corpo";
        }
        return "/usuario/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid User user, BindingResult result, 
                         RedirectAttributes attr, ModelMap model, HttpServletResponse response) {
        if (result.hasErrors()) {
            return "/usuario/cadastro :: corpo";
        }

        try {
            service.salvar(user);
            attr.addFlashAttribute("success", "Usuário salvo com sucesso.");
            response.setHeader("HX-Refresh", "true");
            return "";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "/usuario/cadastro :: corpo";
        }
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id, RedirectAttributes attr, HttpServletResponse response) {
        service.excluir(id);
        attr.addFlashAttribute("success", "Usuário excluído com sucesso.");
        
        response.setHeader("HX-Refresh", "true");
    }
}
