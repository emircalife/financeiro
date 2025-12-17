package com.calife.financeiro.dao;

import com.calife.financeiro.domain.DTO.MesAnoDTO;
import com.calife.financeiro.domain.LancamentoReceita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LancamentoReceitaDao {
    void save(LancamentoReceita lancamentoReceita);
    void update(LancamentoReceita lancamentoReceita);
    void delete(Long id);
    LancamentoReceita findById(Long id);
    List<LancamentoReceita> findAll();
    Page<LancamentoReceita> findByMesAndAno(int mes, int ano, Pageable pageable);
    List<MesAnoDTO> getLancamentoReceitaMesAno();
}
