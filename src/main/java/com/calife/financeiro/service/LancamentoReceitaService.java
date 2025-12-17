package com.calife.financeiro.service;

import com.calife.financeiro.domain.LancamentoReceita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface LancamentoReceitaService {
    void salvar(LancamentoReceita lancamentoReceita);
    void editar(LancamentoReceita lancamentoReceita);
    void excluir(Long id);
    LancamentoReceita buscarPorId(Long id);
    List<LancamentoReceita> buscarTodos();
    Page<LancamentoReceita> buscarTodos(Pageable pageable);
    Page<LancamentoReceita> buscarPorMesAno(int mes, int ano, Pageable pageable);
}
