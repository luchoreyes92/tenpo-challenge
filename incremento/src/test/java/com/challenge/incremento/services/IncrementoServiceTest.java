package com.challenge.incremento.services;

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
import org.springframework.web.reactive.function.client.WebClient;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IncrementoServiceTest {

    @Autowired
    IncrementoService incrementoService;
    @MockBean
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClientMock;
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersMock;
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriMock;
    @Mock
    private WebClient.RequestBodySpec requestBodyMock;
    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriMock;
    @Mock
    private WebClient.ResponseSpec responseMock;

    @Before
    public void setUp() {
        Mockito.mock(IncrementoService.class);
        when(webClientBuilder.build()).thenReturn(webClientMock);
        webClientMock = webClientBuilder.build();
    }

    @Test
    public void calcularIncrementoOK() {
        Double expected = 2.0;
        Double result = incrementoService.calcularIncremento(1.0, 1.0);
        assertEquals(expected, result);
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
