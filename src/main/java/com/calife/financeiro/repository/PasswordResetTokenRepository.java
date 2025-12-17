package com.calife.financeiro.repository;

import com.calife.financeiro.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    @Modifying
    @Query("""
       update PasswordResetToken t
          set t.used = true
        where t.email = :email
          and t.used = false
       """)
    int invalidateActiveTokensByEmail(@Param("email") String email);

    @Modifying
    @Query("""
       delete from PasswordResetToken t
        where t.used = true
           or t.expiresAt < :now
       """)
    int deleteUsedOrExpired(@Param("now") LocalDateTime now);

}
