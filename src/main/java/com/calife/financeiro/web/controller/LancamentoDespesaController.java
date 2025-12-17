package com.calife.financeiro.web.controller;

import com.calife.financeiro.domain.LancamentoDespesa;
import com.calife.financeiro.service.CategoriaDespesaService;
import com.calife.financeiro.service.LancamentoDespesaService;
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
@RequestMapping("/lancamento_despesa")
public class LancamentoDespesaController {

    private final LancamentoDespesaService lancamentoDespesaService;
    private final CategoriaDespesaService categoriaDespesaService;
    private final UserService userService;

    public LancamentoDespesaController(LancamentoDespesaService lancamentoDespesaService, CategoriaDespesaService categoriaDespesaService, UserService userService) {
        this.lancamentoDespesaService = lancamentoDespesaService;
        this.categoriaDespesaService = categoriaDespesaService;
        this.userService = userService;
    }

    // ... other methods ...
    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model, HttpServletRequest request) {
        model.addAttribute("lancamentoDespesa", new LancamentoDespesa());
        model.addAttribute("categoriasDespesa", categoriaDespesaService.buscarTodos());
        model.addAttribute("users", userService.buscarTodos());
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/lancamento_despesa/cadastro :: corpo";
        }
        return "/lancamento_despesa/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model, HttpServletRequest request,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("lancamentosDespesa", lancamentoDespesaService.buscarTodos(pageable));
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/lancamento_despesa/lista :: corpo";
        }
        return "/lancamento_despesa/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(ModelMap model, @PathVariable Long id, HttpServletRequest request) {
        model.addAttribute("lancamentoDespesa", lancamentoDespesaService.buscarPorId(id));
        model.addAttribute("categoriasDespesa", categoriaDespesaService.buscarTodos());
        model.addAttribute("users", userService.buscarTodos());
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/lancamento_despesa/cadastro :: corpo";
        }
        return "/lancamento_despesa/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid LancamentoDespesa lancamentoDespesa, BindingResult result, 
                         RedirectAttributes attr, HttpServletResponse response, ModelMap model) {
        
        model.addAttribute("categoriasDespesa", categoriaDespesaService.buscarTodos());
        model.addAttribute("users", userService.buscarTodos());

        if (result.hasErrors()) {
            return "/lancamento_despesa/cadastro :: corpo";
        }

        lancamentoDespesaService.salvar(lancamentoDespesa);
        attr.addFlashAttribute("success", "Lançamento de despesa salvo com sucesso.");
        
        response.setHeader("HX-Refresh", "true");
        return "";
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id, RedirectAttributes attr, HttpServletResponse response) {
        lancamentoDespesaService.excluir(id);
        attr.addFlashAttribute("success", "Lançamento de despesa excluído com sucesso.");
        
        response.setHeader("HX-Refresh", "true");
    }
}
