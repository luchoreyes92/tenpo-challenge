package com.challenge.autenticacion.repositories;

import com.challenge.autenticacion.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);
}
