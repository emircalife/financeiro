package com.calife.financeiro.service;

public interface EmailService {
    void send(String to, String subject, String body);
}
