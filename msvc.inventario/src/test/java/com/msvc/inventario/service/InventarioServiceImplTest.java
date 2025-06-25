package com.msvc.inventario.service;

import com.msvc.inventario.models.entities.Inventario;
import com.msvc.inventario.repositories.InventarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioServiceImplTest {

    @InjectMocks
    private InventarioServiceImpl inventarioService;

    @Mock
    private InventarioRepository inventarioRepository;

    private Inventario inventarioEjemplo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventarioEjemplo = new Inventario(1L, 100L, 15, LocalDate.now());
    }

    @Test
    void findAll_deberiaRetornarListaInventarios() {
        List<Inventario> lista = Arrays.asList(inventarioEjemplo);
        when(inventarioRepository.findAll()).thenReturn(lista);

        List<Inventario> resultado = inventarioService.findAll();

        assertEquals(1, resultado.size());
        verify(inventarioRepository, times(1)).findAll();
    }

    @Test
    void findById_cuandoExiste_deberiaRetornarInventario() {
        when(inventarioRepository.findById(1L)).thenReturn(Optional.of(inventarioEjemplo));

        Inventario resultado = inventarioService.findById(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdInventario());
    }

    @Test
    void findById_cuandoNoExiste_deberiaLanzarExcepcion() {
        when(inventarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            inventarioService.findById(1L);
        });

        assertEquals("Inventario no encontrado", ex.getMessage());
    }

    @Test
    void save_deberiaGuardarInventario() {
        when(inventarioRepository.save(any(Inventario.class))).thenReturn(inventarioEjemplo);

        Inventario resultado = inventarioService.save(inventarioEjemplo);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdInventario());
        verify(inventarioRepository, times(1)).save(inventarioEjemplo);
    }

    @Test
    void deleteById_deberiaEliminarInventario() {
        doNothing().when(inventarioRepository).deleteById(1L);

        inventarioService.deleteById(1L);

        verify(inventarioRepository, times(1)).deleteById(1L);
    }
}