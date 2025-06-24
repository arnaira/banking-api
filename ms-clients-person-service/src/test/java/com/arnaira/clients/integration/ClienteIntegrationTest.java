package com.arnaira.clients.integration;


import com.arnaira.clients.dto.request.ClienteRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Disabled
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = "/data.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void CrearCliente() throws Exception{

        ClienteRequestDto request = new ClienteRequestDto();
        request.setPersonaId(4L);
        request.setClienteId("ARIVERA");
        request.setContrasena("clave");

        String requestBody =objectMapper.writeValueAsString(request);
        mockMvc.perform(post("/cliente").contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.mensaje.codMensaje").value("00"));
    }
}
