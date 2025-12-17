package com.calife.financeiro.dao;

import com.calife.financeiro.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

    @Override
    public User findByEmail(String email) {
        List<User> users = createQuery("SELECT u "+
                                            "FROM User u "+
                                            "WHERE u.email = ?1", email);
        return users.isEmpty() ? null : users.get(0);
    }
}
