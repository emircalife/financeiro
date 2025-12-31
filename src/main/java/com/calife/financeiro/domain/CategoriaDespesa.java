package com.calife.financeiro.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoriaDespesa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaDespesa extends AbstractEntity<Long>{

    @NotBlank
    @Column(name="descricao", nullable = false, unique = true, length = 255)
    private String descricao;

    @NotBlank
    @Column(name="status", nullable = false, length = 255)
    private String status;
}
