package com.msvc.detalleBoleta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import com.msvc.detalleBoleta.service.DetalleBoletaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DetalleBoletaController.class)
class DetalleBoletaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DetalleBoletaService service;

    @Autowired
    private ObjectMapper objectMapper;

    private DetalleBoleta detalle;

    @BeforeEach
    void setUp() {
        detalle = new DetalleBoleta(1L, 1L, 1L, 2, 100.0, 200.0);
    }

    @Test
    @DisplayName("GET /api/v1/detalleBoleta debe retornar lista de detalles")
    void testGetAll() throws Exception {
        when(service.findAll()).thenReturn(List.of(detalle));

        mockMvc.perform(get("/api/v1/detalleBoleta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idDetalleBoleta").value(detalle.getIdDetalleBoleta()));

        verify(service).findAll();
    }

    @Test
    @DisplayName("GET /api/v1/detalleBoleta/{id} debe retornar un detalle")
    void testGetById() throws Exception {
        when(service.findById(1L)).thenReturn(detalle);

        mockMvc.perform(get("/api/v1/detalleBoleta/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDetalleBoleta").value(1L));

        verify(service).findById(1L);
    }

    @Test
    @DisplayName("POST /api/v1/detalleBoleta debe crear un nuevo detalle")
    void testCreate() throws Exception {
        when(service.save(any(DetalleBoleta.class))).thenReturn(detalle);

        mockMvc.perform(post("/api/v1/detalleBoleta")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(detalle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDetalleBoleta").value(1L));
    }

    @Test
    @DisplayName("PUT /api/v1/detalleBoleta/{id} debe actualizar un detalle")
    void testUpdate() throws Exception {
        DetalleBoleta actualizado = new DetalleBoleta(1L, 1L, 1L, 3, 100.0, 300.0);

        when(service.findById(1L)).thenReturn(detalle);
        when(service.save(any(DetalleBoleta.class))).thenReturn(actualizado);

        mockMvc.perform(put("/api/v1/detalleBoleta/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(3));
    }

    @Test
    @DisplayName("DELETE /api/v1/detalleBoleta/{id} debe eliminar un detalle")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/detalleBoleta/1"))
                .andExpect(status().isNoContent());

        verify(service).deleteById(1L);
    }
}