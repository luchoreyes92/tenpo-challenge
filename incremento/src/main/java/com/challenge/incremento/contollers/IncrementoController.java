package com.challenge.incremento.contollers;

import com.challenge.incremento.dtos.IncrementoDTO;
import com.challenge.incremento.services.IncrementoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/incremento")
public class IncrementoController {

    @Autowired
    IncrementoService incrementoService;

    @RequestMapping(value = "/incremento", method = RequestMethod.GET)
    public ResponseEntity<?> calcularIncremento(@NonNull @RequestParam("valor1") Double valor1,
                                                @NonNull @RequestParam("valor2") Double valor2) {

        validarParametros(valor1, valor2);
        IncrementoDTO incremento = IncrementoDTO.builder()
                .Incremento(incrementoService.calcularIncremento(valor1, valor2))
                .build();

        return new ResponseEntity<>(incremento, HttpStatus.OK);
    }

    private void validarParametros(Double valor1, Double valor2) {
        if (valor1 < 0 || valor2 < 0) {
            throw new IllegalArgumentException("Ningún valor puede ser negativo");
        }
    }


}
