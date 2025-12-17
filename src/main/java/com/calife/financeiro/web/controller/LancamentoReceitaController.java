package com.calife.financeiro.web.controller;

import com.calife.financeiro.domain.LancamentoReceita;
import com.calife.financeiro.service.CategoriaReceitaService;
import com.calife.financeiro.service.LancamentoReceitaService;
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
@RequestMapping("/lancamento_receita")
public class LancamentoReceitaController {

    private final LancamentoReceitaService lancamentoReceitaService;
    private final CategoriaReceitaService categoriaReceitaService;
    private final UserService userService;

    public LancamentoReceitaController(LancamentoReceitaService lancamentoReceitaService, CategoriaReceitaService categoriaReceitaService, UserService userService) {
        this.lancamentoReceitaService = lancamentoReceitaService;
        this.categoriaReceitaService = categoriaReceitaService;
        this.userService = userService;
    }

    // ... other methods ...
    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model, HttpServletRequest request) {
        model.addAttribute("lancamentoReceita", new LancamentoReceita());
        model.addAttribute("categoriasReceita", categoriaReceitaService.buscarTodos());
        model.addAttribute("users", userService.buscarTodos());
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/lancamento_receita/cadastro :: corpo";
        }
        return "/lancamento_receita/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model, HttpServletRequest request,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("lancamentosReceita", lancamentoReceitaService.buscarTodos(pageable));
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/lancamento_receita/lista :: corpo";
        }
        return "/lancamento_receita/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
        model.addAttribute("lancamentoReceita", lancamentoReceitaService.buscarPorId(id));
        model.addAttribute("categoriasReceita", categoriaReceitaService.buscarTodos());
        model.addAttribute("users", userService.buscarTodos());
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/lancamento_receita/cadastro :: corpo";
        }
        return "/lancamento_receita/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid LancamentoReceita lancamentoReceita, BindingResult result, 
                         RedirectAttributes attr, HttpServletResponse response, ModelMap model) {
        
        model.addAttribute("categoriasReceita", categoriaReceitaService.buscarTodos());
        model.addAttribute("users", userService.buscarTodos());

        if (result.hasErrors()) {
            return "/lancamento_receita/cadastro :: corpo";
        }

        lancamentoReceitaService.salvar(lancamentoReceita);
        attr.addFlashAttribute("success", "Lançamento de receita salvo com sucesso.");
        
        response.setHeader("HX-Refresh", "true");
        return "";
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id, RedirectAttributes attr, HttpServletResponse response) {
        lancamentoReceitaService.excluir(id);
        attr.addFlashAttribute("success", "Lançamento de receita excluído com sucesso.");
        
        response.setHeader("HX-Refresh", "true");
    }
}
