package com.calife.financeiro.dao;

import com.calife.financeiro.domain.CategoriaDespesa;

import java.util.List;

public interface CategoriaDespesaDao {
    void save(CategoriaDespesa categoriaDespesa);
    void update(CategoriaDespesa categoriaDespesa);
    void delete(Long id);
    CategoriaDespesa findById(Long id);
    List<CategoriaDespesa> findAll();
}
