package com.calife.financeiro.web.controller;

import com.calife.financeiro.service.HomeService;
import com.calife.financeiro.service.LancamentoDespesaService;
import com.calife.financeiro.service.LancamentoReceitaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class HomeController {
    private final LancamentoDespesaService lancamentoDespesaService;
    private final LancamentoReceitaService lancamentoReceitaService;
    private final HomeService homeService;

    public HomeController(LancamentoDespesaService lancamentoDespesaService,
                          LancamentoReceitaService lancamentoReceitaService,
                          HomeService homeService) {
        this.lancamentoDespesaService = lancamentoDespesaService;
        this.lancamentoReceitaService = lancamentoReceitaService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String home(ModelMap model, HttpServletRequest request) {
        LocalDate dataAtual = LocalDate.now();
        int mes = dataAtual.getMonthValue();
        int ano = dataAtual.getYear();

        model.addAttribute("isLoginPage", false);
        model.addAttribute("mesAnoAtual", String.format("%02d/%04d", mes, ano));
        model.addAttribute("listaMesAno", homeService.getMesAno());

        Pageable despesasPageable = PageRequest.of(0, 5);
        Pageable receitasPageable = PageRequest.of(0, 5);
        model.addAttribute("lancamentosDespesa", lancamentoDespesaService.buscarPorMesAno(mes, ano, despesasPageable));
        model.addAttribute("lancamentosReceita", lancamentoReceitaService.buscarPorMesAno(mes, ano, receitasPageable));

        if ("true".equals(request.getHeader("HX-Request"))) {
            return "home :: corpo";
        } else {
            return "/home";
        }
    }

    @GetMapping("/home/lists")
    public String getLists(ModelMap model,
                           @RequestParam("mesAno") String mesAno,
                           @RequestParam(value = "despesasPage", defaultValue = "0") int despesasPage,
                           @RequestParam(value = "receitasPage", defaultValue = "0") int receitasPage) {

        model.addAttribute("mesAnoAtual", mesAno);
        Pageable despesasPageable = PageRequest.of(despesasPage, 5);
        Pageable receitasPageable = PageRequest.of(receitasPage, 5);

        if ("<<<TODOS>>>".equals(mesAno)) {
            model.addAttribute("lancamentosDespesa", lancamentoDespesaService.buscarTodos(despesasPageable));
            model.addAttribute("lancamentosReceita", lancamentoReceitaService.buscarTodos(receitasPageable));
        } else {
            String[] parts = mesAno.split("/");
            int mes = Integer.parseInt(parts[0]);
            int ano = Integer.parseInt(parts[1]);
            model.addAttribute("lancamentosDespesa", lancamentoDespesaService.buscarPorMesAno(mes, ano, despesasPageable));
            model.addAttribute("lancamentosReceita", lancamentoReceitaService.buscarPorMesAno(mes, ano, receitasPageable));
        }
        return "fragments/home-lists-fragment :: lists-content"; // Corrected fragment name
    }
}
