package com.msvc.inventario.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msvc.inventario.models.entities.Inventario;
import com.msvc.inventario.service.InventarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InventarioController.class)
class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventarioService inventarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Inventario inventarioEjemplo;

    @BeforeEach
    void setUp() {
        inventarioEjemplo = new Inventario(1L, 100L, 10, LocalDate.now());
    }

    @Test
    void getAll_deberiaRetornarListaInventarios() throws Exception {
        Mockito.when(inventarioService.findAll()).thenReturn(Arrays.asList(inventarioEjemplo));

        mockMvc.perform(get("/api/v1/inventario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].idInventario", is(inventarioEjemplo.getIdInventario().intValue())))
                .andExpect(jsonPath("$[0].idProducto", is(inventarioEjemplo.getIdProducto().intValue())))
                .andExpect(jsonPath("$[0].stock", is(inventarioEjemplo.getStock())));
    }

    @Test
    void getById_deberiaRetornarInventario() throws Exception {
        Mockito.when(inventarioService.findById(1L)).thenReturn(inventarioEjemplo);

        mockMvc.perform(get("/api/v1/inventario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idInventario", is(inventarioEjemplo.getIdInventario().intValue())))
                .andExpect(jsonPath("$.idProducto", is(inventarioEjemplo.getIdProducto().intValue())))
                .andExpect(jsonPath("$.stock", is(inventarioEjemplo.getStock())));
    }

    @Test
    void create_deberiaCrearInventario() throws Exception {
        Mockito.when(inventarioService.save(Mockito.any(Inventario.class))).thenReturn(inventarioEjemplo);

        mockMvc.perform(post("/api/v1/inventario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventarioEjemplo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idInventario", is(inventarioEjemplo.getIdInventario().intValue())))
                .andExpect(jsonPath("$.stock", is(inventarioEjemplo.getStock())));
    }

    @Test
    void update_deberiaActualizarInventario() throws Exception {
        Inventario inventarioActualizado = new Inventario(1L, 100L, 20, LocalDate.now());
        Mockito.when(inventarioService.findById(1L)).thenReturn(inventarioEjemplo);
        Mockito.when(inventarioService.save(Mockito.any(Inventario.class))).thenReturn(inventarioActualizado);

        mockMvc.perform(put("/api/v1/inventario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inventarioActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stock", is(20)));
    }

    @Test
    void delete_deberiaEliminarInventario() throws Exception {
        Mockito.doNothing().when(inventarioService).deleteById(1L);

        mockMvc.perform(delete("/api/v1/inventario/1"))
                .andExpect(status().isNoContent());
    }
}