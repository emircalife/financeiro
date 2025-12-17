package com.calife.financeiro.service;

import com.calife.financeiro.domain.CategoriaReceita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoriaReceitaService {
    void salvar(CategoriaReceita categoriaReceita);
    void editar(CategoriaReceita categoriaReceita);
    void excluir(Long id);
    CategoriaReceita buscarPorId(Long id);
    List<CategoriaReceita> buscarTodos();
    Page<CategoriaReceita> buscarTodos(Pageable pageable);
}
