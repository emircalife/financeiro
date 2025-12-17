package com.calife.financeiro.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "lancamento_despesa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LancamentoDespesa extends AbstractEntity<Long>{

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_vencimento")
    private LocalDate dataVencimento;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="data_pagamento")
    private LocalDate dataPagamento;

    @NotBlank
    @Column(name="descricao", nullable = false, length = 255)
    private String descricao;

    @NotNull
    @Column(name="valorapagar")
    private Double valorAPagar;

    @Column(name="valor_pago")
    private Double valorPago;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_categoria_despesa_despesa", nullable = false) // FK
    private CategoriaDespesa categoriasDespesa;

    @ManyToOne
    @JoinColumn(name = "id_user_despesa", nullable = false) // FK
    private User user;

    @Column(name="observacoes", length = 255)
    private String observacoes;

    @Column(name="despesa_fixa")
    private Boolean despesaFixa;

    @NotNull
    @Column(name="n_parcela")
    private Integer nParcela;

    @NotNull
    @Column(name="total_parcelas")
    private Integer totalParcelas;

}
