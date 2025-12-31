package com.calife.financeiro.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractEntity<Long>{

    @NotBlank
    @Column(name="username", nullable = false, unique = true, length = 255)
    private String username;

    @NotBlank
    @Column(name="email", nullable = false, unique = true, length = 255)
    private String email;

    @NotBlank
    @Column(name="password", nullable = false, unique = true, length = 255)
    private String password;

    @NotBlank
    @Column(name="repeatPassword", nullable = false, unique = true, length = 255)
    private String repeatPassword;

    @CreationTimestamp
    @Column(name="creationTimestamp")
    private Instant creationTimestamp;

    @UpdateTimestamp
    @Column(name="updateTimestamp")
    private Instant updateTimestamp;

    @PrePersist
    public void prePersist() {
        this.creationTimestamp = Instant.now();
        this.updateTimestamp = null;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateTimestamp = Instant.now();
    }
}
