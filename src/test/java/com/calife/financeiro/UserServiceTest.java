package com.calife.financeiro;

import com.calife.financeiro.repository.UserRepository;
import com.calife.financeiro.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    private UserService service;

    @Nested
    class createUser{

        @Test
        @DisplayName("Should create a user with success!")
        void shouldCreateUserWithSuccess(){



        }

    }
}
