package com.challenge.incremento.services;

import com.challenge.incremento.dtos.ErrorResponseDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IncrementoServiceTest {

    @Autowired
    IncrementoService incrementoService;

    @Before
    public void setUp() {
        Mockito.mock(IncrementoService.class);
    }

    @Test
    public void calcularIncrementoOK() {
        Double expected = 2.0;
        Double result = incrementoService.calcularIncremento(1.0, 1.0);
        assertEquals(expected, result);
    }

    @Test
    public void calcularIncrementoNullValue() {

        assertThrows(HttpServerErrorException.class, () -> {
            incrementoService.calcularIncremento(1.0, 1.0);
        });
    }


//    @Test
//    public void obtenerPorcentajeOK() {
//        PorcentajeDTO mockPorcentaje = PorcentajeDTO.builder().porcentaje(0.5).build();
//        when(webClientMock.get()).thenReturn(requestHeadersUriMock);
//        when(requestHeadersUriMock.uri("/porcentaje-actual")).thenReturn(requestHeadersMock);
//        when(requestHeadersMock.retrieve()).thenReturn(responseMock);
//        when(responseMock.bodyToFlux(PorcentajeDTO.class)).thenReturn(Flux.just(mockPorcentaje));
//        incrementoService.obtenerPorcentaje();
//    }


}
