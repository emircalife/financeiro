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
@Table(name = "lancamentoReceita")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LancamentoReceita extends AbstractEntity<Long>{

    @NotBlank
    @Column(name="descricao", nullable = false, length = 255)
    private String descricao;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="dataAReceber")
    private LocalDate dataAReceber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="dataRecebimento")
    private LocalDate dataRecebimento;

    @NotNull
    @Column(name="valorAReceber")
    private Double valorAReceber;

    @Column(name="valorRecebido")
    private Double valorRecebido;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idCategoriaReceita", nullable = false) // FK
    private CategoriaReceita categoriasReceita;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false) // FK
    private User user;

    @Column(name="observacoes", length = 255)
    private String observacoes;
}
