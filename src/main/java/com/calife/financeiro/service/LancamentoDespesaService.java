package com.calife.financeiro.service;

import com.calife.financeiro.domain.LancamentoDespesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface LancamentoDespesaService {
    void salvar(LancamentoDespesa lancamentoDespesa);
    void editar(LancamentoDespesa lancamentoDespesa);
    void excluir(Long id);
    LancamentoDespesa buscarPorId(Long id);
    List<LancamentoDespesa> buscarTodos();
    Page<LancamentoDespesa> buscarTodos(Pageable pageable);
    Page<LancamentoDespesa> buscarPorMesAno(int mes, int ano, Pageable pageable);
}
