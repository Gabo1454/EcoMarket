package com.msvc.producto.services;

import com.msvc.producto.models.entities.Producto;
import com.msvc.producto.repositories.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    public void testFindById() {
        Producto producto = new Producto(1L, "Producto1", 100.0, "Descripción", 10);
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto found = productoService.findById(1L);
        assertNotNull(found);
        assertEquals("Producto1", found.getNombre());
    }

    @Test
    public void testSave() {
        Producto producto = new Producto(null, "Producto1", 100.0, "Descripción", 10);
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto saved = productoService.save(producto);
        assertNotNull(saved);
        verify(productoRepository).save(producto);
    }
}