package com.msvc.detalleBoleta.service;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import com.msvc.detalleBoleta.repositories.DetalleBoletaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DetalleBoletaServiceTest {

    @Mock
    private DetalleBoletaRepository repository;

    @InjectMocks
    private DetalleBoletaServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Debe retornar todos los detalles")
    void testFindAll() {
        List<DetalleBoleta> lista = List.of(
                new DetalleBoleta(1L, 1L, 1L, 2, 100.0, 200.0),
                new DetalleBoleta(2L, 1L, 2L, 1, 300.0, 300.0)
        );

        when(repository.findAll()).thenReturn(lista);

        List<DetalleBoleta> result = service.findAll();

        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe retornar detalle por ID")
    void testFindById() {
        DetalleBoleta detalle = new DetalleBoleta(1L, 1L, 1L, 2, 100.0, 200.0);
        when(repository.findById(1L)).thenReturn(Optional.of(detalle));

        DetalleBoleta result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdDetalleBoleta());
        verify(repository).findById(1L);
    }

    @Test
    @DisplayName("Debe guardar un detalle")
    void testSave() {
        DetalleBoleta detalle = new DetalleBoleta(null, 1L, 1L, 2, 100.0, 200.0);
        DetalleBoleta saved = new DetalleBoleta(1L, 1L, 1L, 2, 100.0, 200.0);

        when(repository.save(detalle)).thenReturn(saved);

        DetalleBoleta result = service.save(detalle);

        assertNotNull(result.getIdDetalleBoleta());
        verify(repository).save(detalle);
    }

    @Test
    @DisplayName("Debe eliminar un detalle por ID")
    void testDeleteById() {
        Long id = 1L;
        service.deleteById(id);
        verify(repository).deleteById(id);
    }

    @Test
    @DisplayName("Debe lanzar excepción si no encuentra ID")
    void testFindByIdNotFound() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () -> {
            service.findById(999L);
        });

        assertEquals("DetalleBoleta con ID 999 no encontrado", ex.getMessage());
    }
}