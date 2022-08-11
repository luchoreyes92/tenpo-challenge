package com.challenge.autenticacion.services;

import com.challenge.autenticacion.entities.UserEntity;
import com.challenge.autenticacion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean existeUsuario(String username) {
        return userRepository.existsByUsername(username);
    }

    public void registrarUsuario(String username, String passowrd) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passowrd);
        user.setFechaCreacion(LocalDateTime.now());
    }

}
