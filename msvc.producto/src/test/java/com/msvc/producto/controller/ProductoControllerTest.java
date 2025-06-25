package com.msvc.producto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msvc.producto.models.entities.Producto;
import com.msvc.producto.services.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProductoById() throws Exception {
        Producto producto = new Producto(1L, "Producto1", 100.0, "Descripción", 10);
        when(productoService.findById(1L)).thenReturn(producto);

        mockMvc.perform(get("/api/v1/productos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Producto1"));
    }

    @Test
    public void testCreateProducto() throws Exception {
        Producto producto = new Producto(null, "ProductoNuevo", 150.0, "Desc", 20);
        Producto productoGuardado = new Producto(2L, "ProductoNuevo", 150.0, "Desc", 20);

        when(productoService.save(any(Producto.class))).thenReturn(productoGuardado);

        String json = objectMapper.writeValueAsString(producto);

        mockMvc.perform(post("/api/v1/productos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idProducto").value(2L))
                .andExpect(jsonPath("$.nombre").value("ProductoNuevo"));
    }
}