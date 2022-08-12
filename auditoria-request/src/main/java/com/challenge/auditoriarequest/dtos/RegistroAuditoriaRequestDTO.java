package com.challenge.auditoriarequest.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class RegistroAuditoriaRequestDTO {

    private String uri;
    private Integer status;
    private String response;
    private String username;

}
