package com.calife.financeiro.dao;

import com.calife.financeiro.domain.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    void update(User user);
    void delete(Long id);
    User findById(Long id);
    List<User> findAll();
    User findByEmail(String email); // New method
}
