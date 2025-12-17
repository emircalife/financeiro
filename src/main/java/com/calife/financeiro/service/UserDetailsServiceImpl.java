package com.calife.financeiro.service;

import com.calife.financeiro.dao.UserDao;
import com.calife.financeiro.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário nõ encontrado com o email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
