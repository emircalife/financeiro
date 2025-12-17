package com.calife.financeiro.domain.DTO;

import com.calife.financeiro.domain.CategoriaDespesa;
import com.calife.financeiro.domain.User;

import java.time.LocalDate;

public record LancamentoDespesaDTO(String descricao, LocalDate dataVencimento, LocalDate dataPagamento,
                                   Double valorAPagar, Double valorPago, CategoriaDespesa categoriaDespesa,
                                   User user, String observacoes, Boolean despesaFixa, Integer nParcela,
                                   Integer totalParcelas) {
}