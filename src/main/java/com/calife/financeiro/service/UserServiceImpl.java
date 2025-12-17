package com.calife.financeiro.service;

import com.calife.financeiro.domain.User;
import com.calife.financeiro.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void salvar(User user) {
        if (user.getPassword() != null && user.getRepeatPassword() != null && user.getPassword().equals(user.getRepeatPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRepeatPassword(user.getPassword());
        } else {
            throw new IllegalArgumentException("As senhas não coincidem.");
        }

        repository.save(user);
    }

    @Override
    public void editar(User user) {
        // You might want to handle password changes more carefully here
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // Keep the old password if a new one isn't provided
            User existingUser = repository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                user.setPassword(existingUser.getPassword());
            }
        }
        repository.save(user);
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> buscarTodos(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
