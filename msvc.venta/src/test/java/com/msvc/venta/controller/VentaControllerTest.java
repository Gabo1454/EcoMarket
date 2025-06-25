package com.msvc.venta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msvc.venta.models.entities.Venta;
import com.msvc.venta.service.VentaServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VentaController.class)
public class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VentaServiceImpl ventaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListar() throws Exception {
        Venta venta1 = new Venta(1L, LocalDate.now(), 100.0, 1L, 1L);
        Venta venta2 = new Venta(2L, LocalDate.now(), 200.0, 2L, 2L);

        when(ventaService.listarVentas()).thenReturn(Arrays.asList(venta1, venta2));

        mockMvc.perform(get("/api/v1/ventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idVenta").value(1))
                .andExpect(jsonPath("$[1].total").value(200.0));
    }

    @Test
    public void testDetalle() throws Exception {
        Venta venta = new Venta(1L, LocalDate.now(), 150.0, 1L, 1L);
        when(ventaService.buscarVenta(1L)).thenReturn(venta);

        mockMvc.perform(get("/api/v1/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(150.0));
    }

    @Test
    public void testCrear() throws Exception {
        Venta venta = new Venta(null, LocalDate.now(), 180.0, 3L, 3L);
        Venta ventaGuardada = new Venta(3L, LocalDate.now(), 180.0, 3L, 3L);

        when(ventaService.guardarVenta(any(Venta.class))).thenReturn(ventaGuardada);

        String json = objectMapper.writeValueAsString(venta);

        mockMvc.perform(post("/api/v1/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idVenta").value(3));
    }

    @Test
    public void testActualizar() throws Exception {
        Venta venta = new Venta(1L, LocalDate.now(), 200.0, 1L, 1L);

        when(ventaService.actualizarVenta(any(Venta.class))).thenReturn(venta);

        String json = objectMapper.writeValueAsString(venta);

        mockMvc.perform(put("/api/v1/ventas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(200.0));
    }

    @Test
    public void testEliminar() throws Exception {
        mockMvc.perform(delete("/api/v1/ventas/1"))
                .andExpect(status().isNoContent());
    }
}
