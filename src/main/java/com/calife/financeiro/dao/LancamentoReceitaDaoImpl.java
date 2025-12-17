package com.calife.financeiro.dao;

import com.calife.financeiro.domain.DTO.MesAnoDTO;
import com.calife.financeiro.domain.LancamentoReceita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import jakarta.persistence.Query;

@Repository
public class LancamentoReceitaDaoImpl extends AbstractDao<LancamentoReceita, Long> implements LancamentoReceitaDao {

    @Override
    public Page<LancamentoReceita> findByMesAndAno(int mes, int ano, Pageable pageable) {

        String sql = """
        SELECT *
        FROM lancamento_receita lr
        WHERE MONTH(lr.dataAReceber) = ?1
          AND YEAR(lr.dataAReceber) = ?2
        """;

        String sqlCount = """
        SELECT COUNT(*)
        FROM lancamento_receita lr
        WHERE MONTH(lr.dataAReceber) = ?1
          AND YEAR(lr.dataAReceber) = ?2
        """;

        Query query = getEntityManager().createNativeQuery(sql, LancamentoReceita.class);
        query.setParameter(1, mes);
        query.setParameter(2, ano);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        @SuppressWarnings("unchecked")
        List<LancamentoReceita> resultados = query.getResultList();

        Query countQuery = getEntityManager().createNativeQuery(sqlCount);
        countQuery.setParameter(1, mes);
        countQuery.setParameter(2, ano);

        Number total = (Number) countQuery.getSingleResult();

        return new PageImpl<LancamentoReceita>(resultados, pageable, total.longValue());
    }


    @Override
    public List<MesAnoDTO> getLancamentoReceitaMesAno() {
        // Using JPQL constructor expression for type safety
        String jpql = "SELECT new com.calife.financeiro.domain.DTO.MesAnoDTO(" +
                      "   MONTH(lr.dataAReceber), " +
                      "   YEAR(lr.dataAReceber)" +
                      ") " +
                      "FROM LancamentoReceita lr " +
                      "GROUP BY MONTH(lr.dataAReceber), YEAR(lr.dataAReceber) "+
                      "ORDER BY MONTH(lr.dataAReceber) DESC, YEAR(lr.dataAReceber) DESC ";

        return getEntityManager().createQuery(jpql, MesAnoDTO.class).getResultList();
    }
}
