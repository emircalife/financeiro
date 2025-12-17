package com.calife.financeiro.dao;

import com.calife.financeiro.domain.CategoriaDespesa;
import com.calife.financeiro.domain.CategoriaReceita;

import java.util.List;

public interface CategoriaReceitaDao {
    void save(CategoriaReceita categoriaReceita);
    void update(CategoriaReceita categoriaReceita);
    void delete(Long id);
    CategoriaReceita findById(Long id);
    List<CategoriaReceita> findAll();
}
