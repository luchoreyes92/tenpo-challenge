package com.challenge.autenticacion.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class SingUpDTO {
    private String username;
    private String password;
}
