package com.challenge.incremento.integrations;

import com.challenge.incremento.contollers.IncrementoController;
import com.challenge.incremento.dtos.ErrorResponseDTO;
import com.challenge.incremento.services.IncrementoService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IncrementoIntegrationTest {

    private MockMvc mockMvc;
    private final String URI_CALCULAR_INCREMENTO = "/incremento";
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    IncrementoController incrementoController;

    @Before
    public void setUp() {
        Mockito.mock(IncrementoService.class);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void calcularIncrementoTestOK() throws Exception {
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get(URI_CALCULAR_INCREMENTO)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("valor1", "1")
                        .param("valor2", "1")
                )
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(result);
        assertNotNull(jsonNode.get("incremento"));
        assertTrue(jsonNode.get("incremento").asDouble() >= 2.0);
        assertTrue(jsonNode.get("incremento").asDouble() <= 4.0);
    }

    @Test
    public void calcularIncrementoMissingServletRequestParameterExceptionTest() throws Exception {
        String expected = "El parÃ¡metro valor1 es requerido";
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get(URI_CALCULAR_INCREMENTO)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("valor2", "1")
                ).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(result);
        assertNotNull(jsonNode.get("mensaje"));
        assertEquals(expected, jsonNode.get("mensaje").asText());
    }

    @Test
    public void calcularIncrementoIllegalArgumentExceptionTest() throws Exception {
        String expected = "NingÃºn valor puede ser negativo";
        String result = mockMvc
                .perform(MockMvcRequestBuilders.get(URI_CALCULAR_INCREMENTO)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .param("valor1", "-1")
                        .param("valor2", "1")
                ).andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(result);
        assertNotNull(jsonNode.get("mensaje"));
        assertEquals(expected, jsonNode.get("mensaje").asText());
    }

}
