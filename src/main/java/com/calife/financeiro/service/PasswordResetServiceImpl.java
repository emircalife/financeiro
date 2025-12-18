package com.calife.financeiro.service;

import com.calife.financeiro.domain.PasswordResetToken;
import com.calife.financeiro.domain.User;
import com.calife.financeiro.repository.PasswordResetTokenRepository;
import com.calife.financeiro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;
    private final UserService userService;

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.reset-password.ttl-minutes:30}")
    private int ttlMinutes;

    public PasswordResetServiceImpl(
            UserRepository userRepository,
            PasswordResetTokenRepository tokenRepository,
            EmailService emailService,
            UserService userService
    ) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void requestReset(String email) {
        // Segurança: sempre responde igual (não revela se existe ou não)
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return;
        }

        tokenRepository.invalidateActiveTokensByEmail(email);

        String token = UUID.randomUUID().toString();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(ttlMinutes);

        PasswordResetToken prt = new PasswordResetToken();
        prt.setEmail(email);
        prt.setToken(token);
        prt.setExpiresAt(expiresAt);
        prt.setUsed(false);
        tokenRepository.save(prt);

        String link = baseUrl + "/resetar-senha?token=" + token;

        String body = """
            Olá,
            
            Você solicitou a redefinição de senha.
            Clique no link abaixo para criar uma nova senha:
            
            %s
            
            Este link expira em %d minutos.
            Se não foi você, ignore este e-mail.
            """.formatted(link, ttlMinutes);

        emailService.send(email, "Redefinição de senha", body);
    }

    @Override
    public boolean isTokenValid(String token) {
        return tokenRepository.findByToken(token)
                .filter(t -> !t.isUsed())
                .filter(t -> !t.isExpired())
                .isPresent();
    }

    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken prt = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (prt.isUsed() || prt.isExpired()) {
            throw new RuntimeException("Token expirado ou já utilizado");
        }

        User user = userRepository.findByEmail(prt.getEmail());
        if (user == null) {
            throw new RuntimeException("Usuário não encontrado");
        }

        user.setPassword(newPassword); // o UserServiceImpl já aplica BCrypt
        userService.editar(user);

        prt.setUsed(true);
        tokenRepository.save(prt);
    }
}
