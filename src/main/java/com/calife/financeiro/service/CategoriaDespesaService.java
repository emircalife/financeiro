package com.calife.financeiro.service;

import com.calife.financeiro.domain.CategoriaDespesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CategoriaDespesaService {
    void salvar(CategoriaDespesa categoriaDespesa);
    void editar(CategoriaDespesa categoriaDespesa);
    void excluir(Long id);
    CategoriaDespesa buscarPorId(Long id);
    List<CategoriaDespesa> buscarTodos();
    Page<CategoriaDespesa> buscarTodos(Pageable pageable);
}
