package com.calife.financeiro.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categoria_receita")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoriaReceita extends AbstractEntity<Long>{

    @NotBlank
    @Column(name="descricao", nullable = false, unique = true, length = 255)
    private String descricao;

    @NotBlank
    @Column(name="status", nullable = false, length = 255)
    private String status;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
