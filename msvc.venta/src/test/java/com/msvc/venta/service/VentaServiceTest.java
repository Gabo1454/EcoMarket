package com.msvc.venta.service;

import com.msvc.venta.exception.VentaException;
import com.msvc.venta.models.entities.Venta;
import com.msvc.venta.repositories.VentaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VentaServiceTest {

    @Mock
    private VentaRepository ventaRepository;

    @InjectMocks
    private VentaServiceImpl ventaService;

    @Test
    public void testBuscarVenta_Exists() {
        Venta venta = new Venta(1L, LocalDate.now(), 200.0, 1L, 1L);
        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));

        Venta found = ventaService.buscarVenta(1L);
        assertNotNull(found);
        assertEquals(200.0, found.getTotal());
    }

    @Test
    public void testBuscarVenta_NotFound() {
        when(ventaRepository.findById(1L)).thenReturn(Optional.empty());

        VentaException thrown = assertThrows(VentaException.class, () -> {
            ventaService.buscarVenta(1L);
        });

        assertTrue(thrown.getMessage().contains("Venta con ID 1 no encontrada"));
    }

    @Test
    public void testGuardarVenta() {
        Venta venta = new Venta(null, LocalDate.now(), 300.0, 2L, 2L);
        when(ventaRepository.save(venta)).thenReturn(venta);

        Venta saved = ventaService.guardarVenta(venta);
        assertNotNull(saved);
        verify(ventaRepository).save(venta);
    }

    @Test
    public void testActualizarVenta() {
        Venta ventaExistente = new Venta(1L, LocalDate.now(), 100.0, 1L, 1L);
        Venta ventaUpdate = new Venta(1L, LocalDate.now(), 150.0, 1L, 1L);

        when(ventaRepository.findById(1L)).thenReturn(Optional.of(ventaExistente));
        when(ventaRepository.save(any(Venta.class))).thenReturn(ventaUpdate);

        Venta updated = ventaService.actualizarVenta(ventaUpdate);
        assertEquals(150.0, updated.getTotal());
    }

    @Test
    public void testEliminarVenta() {
        Venta venta = new Venta(1L, LocalDate.now(), 100.0, 1L, 1L);

        when(ventaRepository.findById(1L)).thenReturn(Optional.of(venta));
        doNothing().when(ventaRepository).delete(venta);

        ventaService.eliminarVenta(1L);

        verify(ventaRepository).delete(venta);
    }
}

