package com.challenge.incremento.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ErrorResponseDTO {

    private String mensaje;

}
