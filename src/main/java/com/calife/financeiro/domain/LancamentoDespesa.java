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
@Table(name = "lancamentoDespesa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LancamentoDespesa extends AbstractEntity<Long>{

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="dataVencimento")
    private LocalDate dataVencimento;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="dataPagamento")
    private LocalDate dataPagamento;

    @NotBlank
    @Column(name="descricao", nullable = false, length = 255)
    private String descricao;

    @NotNull
    @Column(name="valorAPagar")
    private Double valorAPagar;

    @Column(name="valorPago")
    private Double valorPago;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idCategoriaDespesa", nullable = false) // FK
    private CategoriaDespesa categoriasDespesa;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false) // FK
    private User user;

    @Column(name="observacoes", length = 255)
    private String observacoes;

    @Column(name="despesaFixa")
    private Boolean despesaFixa;

    @NotNull
    @Column(name="nParcela")
    private Integer nParcela;

    @NotNull
    @Column(name="totalParcelas")
    private Integer totalParcelas;

}
