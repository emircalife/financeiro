package com.calife.financeiro.jobs;

import com.calife.financeiro.repository.PasswordResetTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class PasswordResetTokenCleanupJob {

    private final PasswordResetTokenRepository tokenRepository;

    public PasswordResetTokenCleanupJob(PasswordResetTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // roda a cada 1 hora
    @Scheduled(cron = "0 0 */1 * * *")
    @Transactional
    public void cleanup() {
        tokenRepository.deleteUsedOrExpired(LocalDateTime.now());
    }
}
