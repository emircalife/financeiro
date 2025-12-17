package com.calife.financeiro.web.controller;

import com.calife.financeiro.domain.CategoriaReceita;
import com.calife.financeiro.service.CategoriaReceitaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/categoria_receita")
public class CategoriaReceitaController {

    private final CategoriaReceitaService service;

    public CategoriaReceitaController(CategoriaReceitaService service) {
        this.service = service;
    }

    // ... other methods ...
    @GetMapping("/cadastrar")
    public String cadastrar(ModelMap model, HttpServletRequest request) {
        model.addAttribute("categoriaReceita", new CategoriaReceita());
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/categoria_receita/cadastro :: corpo";
        }
        return "/categoria_receita/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model, HttpServletRequest request,
                         @RequestParam(value = "page", defaultValue = "0") int page,
                         @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("categoriasReceita", service.buscarTodos(pageable));
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/categoria_receita/lista :: corpo";
        }
        return "/categoria_receita/lista";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, ModelMap model, HttpServletRequest request) {
        model.addAttribute("categoriaReceita", service.buscarPorId(id));
        if ("true".equals(request.getHeader("HX-Request"))) {
            return "/categoria_receita/cadastro :: corpo";
        }
        return "/categoria_receita/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid CategoriaReceita categoriaReceita, BindingResult result, 
                         RedirectAttributes attr, HttpServletResponse response, ModelMap model) {
        if (result.hasErrors()) {
            return "/categoria_receita/cadastro :: corpo";
        }

        service.salvar(categoriaReceita);
        attr.addFlashAttribute("success", "Categoria de receita salva com sucesso.");
        
        response.setHeader("HX-Refresh", "true");
        return "";
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable("id") Long id, RedirectAttributes attr, HttpServletResponse response) {
        try {
            service.excluir(id);
            attr.addFlashAttribute("success", "Categoria de receita excluída com sucesso.");
        } catch (DataIntegrityViolationException e) {
            attr.addFlashAttribute("error", "Não é possível excluir esta categoria, pois ela já está em uso.");
        }
        
        response.setHeader("HX-Refresh", "true");
    }
}
