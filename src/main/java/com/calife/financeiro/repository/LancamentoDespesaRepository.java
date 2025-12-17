package com.calife.financeiro.repository;

import com.calife.financeiro.domain.LancamentoDespesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoDespesaRepository extends JpaRepository<LancamentoDespesa, Long> {

    @Query("SELECT ld "+
            "FROM LancamentoDespesa ld "+
            "WHERE MONTH(ld.dataVencimento) = :mes "+
            "  AND YEAR(ld.dataVencimento) = :ano "+
            "ORDER BY MONTH(ld.dataVencimento) DESC, "+
            "         YEAR(ld.dataVencimento) DESC, "+
            "         ld.id DESC")
    Page<LancamentoDespesa> findByMesAndAno(@Param("mes") int mes, @Param("ano") int ano, Pageable pageable);
}
