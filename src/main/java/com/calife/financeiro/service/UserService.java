package com.calife.financeiro.service;

import com.calife.financeiro.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    void salvar(User user);
    void editar(User user);
    void excluir(Long id);
    User buscarPorId(Long id);
    List<User> buscarTodos();
    Page<User> buscarTodos(Pageable pageable);
    User findByEmail(String email); // Add this method
}
