package com.calife.financeiro.domain.DTO;

import com.calife.financeiro.domain.CategoriaReceita;
import com.calife.financeiro.domain.User;

import java.time.LocalDate;

public record LancamentoReceitaDTO(String descricao, LocalDate dataAReceber, LocalDate dataRecebimento,
                                   Double valorAReceber, Double valorRecebido, CategoriaReceita categoriaReceita,
                                   User user, String observacoes) {
}