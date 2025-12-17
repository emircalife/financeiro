package com.calife.financeiro.service;

public interface PasswordResetService {
    void requestReset(String email);
    void resetPassword(String token, String newPassword);
    boolean isTokenValid(String token);
}
