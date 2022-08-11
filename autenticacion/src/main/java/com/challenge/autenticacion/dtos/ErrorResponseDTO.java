package com.challenge.autenticacion.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponseDTO {

    private String mensaje;

}
