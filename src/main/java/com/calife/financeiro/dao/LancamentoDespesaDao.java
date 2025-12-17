package com.calife.financeiro.dao;

import com.calife.financeiro.domain.DTO.MesAnoDTO;
import com.calife.financeiro.domain.LancamentoDespesa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface LancamentoDespesaDao {
    void save(LancamentoDespesa lancamentoDespesa);
    void update(LancamentoDespesa lancamentoDespesa);
    void delete(Long id);
    LancamentoDespesa findById(Long id);
    List<LancamentoDespesa> findAll();
    Page<LancamentoDespesa> findByMesAndAno(int mes, int ano, Pageable pageable);
    List<MesAnoDTO> getLancamentoDespesaMesAno();
}
