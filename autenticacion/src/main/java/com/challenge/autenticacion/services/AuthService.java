package com.challenge.autenticacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserService userService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public void login(String username, String password) {

    }

    public void singUp(String username, String password) {
        validateUsername(username);
        String encodPassword = bCryptPasswordEncoder.encode(password);
        userService.registrarUsuario(username, encodPassword);
    }

    private void validateUsername(String username) {
        if (userService.existeUsuario(username)) {
            throw new IllegalArgumentException("Usuario existente");
        }
    }
}
