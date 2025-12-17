package com.calife.financeiro.dao;

import com.calife.financeiro.domain.DTO.MesAnoDTO;
import com.calife.financeiro.domain.LancamentoDespesa;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public class LancamentoDespesaDaoImpl extends AbstractDao<LancamentoDespesa, Long> implements LancamentoDespesaDao {
    @Override
    public Page<LancamentoDespesa> findByMesAndAno(int mes, int ano, Pageable pageable) {

        String sql = """
                    SELECT * 
                    FROM lancamento_despesa lr
                    WHERE MONTH(lr.data_vencimento) = ?1
                      AND YEAR(lr.data_vencimento) = ?2
                    ORDER BY MONTH(lr.data_vencimento) DESC,
                             YEAR(lr.data_vencimento) DESC, 
                             lr.id DESC
                    
        """;

        String sqlCount = """
                    SELECT COUNT(*)
                    FROM lancamento_despesa lr
                    WHERE MONTH(lr.data_vencimento) = ?1
                      AND YEAR(lr.data_vencimento) = ?2
        """;

        Query query = getEntityManager().createNativeQuery(sql, LancamentoDespesa.class);
        query.setParameter(1, mes);
        query.setParameter(2, ano);

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        @SuppressWarnings("unchecked")
        List<LancamentoDespesa> resultados = query.getResultList();

        Query countQuery = getEntityManager().createNativeQuery(sqlCount);
        countQuery.setParameter(1, mes);
        countQuery.setParameter(2, ano);

        Number total = (Number) countQuery.getSingleResult();

        return new PageImpl<>(resultados, pageable, total.longValue());
    }


    @Override
    public List<MesAnoDTO> getLancamentoDespesaMesAno() {
        // Using JPQL constructor expression for type safety
        String jpql = "SELECT new com.calife.financeiro.domain.DTO.MesAnoDTO " +
                      "  (MONTH(ld.dataVencimento), " +
                      "  YEAR(ld.dataVencimento)"+
                      ") " +
                      "FROM LancamentoDespesa ld " +
                      "GROUP BY MONTH(ld.dataVencimento), " +
                      "         YEAR(ld.dataVencimento)";

        return getEntityManager().createQuery(jpql, MesAnoDTO.class).getResultList();
    }
}
