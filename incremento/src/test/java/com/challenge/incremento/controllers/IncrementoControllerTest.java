package com.challenge.incremento.controllers;

import com.challenge.incremento.contollers.IncrementoController;
import com.challenge.incremento.dtos.ErrorResponseDTO;
import com.challenge.incremento.dtos.IncrementoDTO;
import com.challenge.incremento.services.IncrementoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IncrementoControllerTest {

    @MockBean
    IncrementoService incrementoService;
    @Autowired
    IncrementoController incrementoController;

    @Before
    public void setUp() {
        Mockito.mock(IncrementoService.class);
    }

    @Test
    public void calcularIncrementoTestOK() {
        Double expected = 5.0;
        when(incrementoService.calcularIncremento(any(), any())).thenReturn(expected);
        ResponseEntity<?> result = incrementoController.calcularIncremento(1.0, 1.0);
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(expected, ((IncrementoDTO) result.getBody()).getIncremento());
    }

    @Test
    public void calcularIncrementoHttpServerErrorExceptionTest() {
        String expected = "No es posible calcular el incremento";
        when(incrementoService.calcularIncremento(any(), any()))
                .thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "No es posible calcular el incremento"));
        ResponseEntity<?> result = incrementoController.calcularIncremento(1.0, 1.0);
        assertNotNull(result.getBody());
        assertEquals(result.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(expected, ((ErrorResponseDTO) result.getBody()).getMensaje());
    }

}
