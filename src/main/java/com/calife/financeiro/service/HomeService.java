package com.calife.financeiro.service;

import com.calife.financeiro.domain.CategoriaDespesa;
import com.calife.financeiro.domain.CategoriaReceita; // Import CategoriaReceita
import com.calife.financeiro.domain.DTO.MesAnoDTO;

import java.util.List;

public interface HomeService {
    List<MesAnoDTO> getLancamentoDespesaMesAno();
    List<MesAnoDTO> getLancamentoReceitaMesAno();

    List<String> getMesAno();

    CategoriaDespesa buscarPorCategoriaDespesaId(Long id);
    CategoriaReceita buscarPorCategoriaReceitaId(Long id);
    List<CategoriaDespesa> buscarCategoriaDespesaTodos();
    List<CategoriaReceita> buscarCategoriaReceitaTodos();
}
