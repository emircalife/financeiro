package com.calife.financeiro.repository;

import com.calife.financeiro.domain.CategoriaReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaReceitaRepository extends JpaRepository<CategoriaReceita, Long> {
}
