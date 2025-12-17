package com.calife.financeiro.repository;

import com.calife.financeiro.domain.LancamentoReceita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoReceitaRepository extends JpaRepository<LancamentoReceita, Long> {

    @Query("SELECT lr FROM LancamentoReceita lr "+
            "WHERE MONTH(lr.dataAReceber) = :mes "+
            "  AND YEAR(lr.dataAReceber) = :ano "+
            "ORDER BY MONTH(lr.dataAReceber) DESC, "+
            "         YEAR(lr.dataAReceber) DESC, "+
            "         lr.id DESC")
    Page<LancamentoReceita> findByMesAndAno(@Param("mes") int mes, @Param("ano") int ano, Pageable pageable);
}
