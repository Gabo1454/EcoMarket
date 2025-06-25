package com.msvc.boleta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.msvc.boleta.client.ClienteService;
import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.service.BoletaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoletaController.class)
public class BoletaControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoletaService boletaService;

    @Test
    void obtenerTodasLasBoletas() throws Exception {
        Boleta boleta1 = new Boleta(1L, 1L, LocalDate.now(), 1500.0);
        Boleta boleta2 = new Boleta(2L, 2L, LocalDate.now(), 2500.0);
        when(boletaService.findAll()).thenReturn(List.of(boleta1, boleta2));

        mockMvc.perform(get("/api/v1/boletas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void crearBoleta() throws Exception {
        Boleta boleta = new Boleta(null, 1L, LocalDate.now(), 3000.0);
        Boleta guardada = new Boleta(1L, 1L, boleta.getFechaBoleta(), 3000.0);

        when(boletaService.save(any(Boleta.class))).thenReturn(guardada);

        mockMvc.perform(post("/api/v1/boletas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boleta)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idBoleta").value(1));
        ;
    }


}
