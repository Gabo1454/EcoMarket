package com.msvc.detalleBoleta.repositories;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DetalleBoletaRepositoryTest {

    @Autowired
    private DetalleBoletaRepository repository;

    @Test
    @DisplayName("Guardar y buscar detalle por ID")
    void saveAndFindById() {
        DetalleBoleta detalle = new DetalleBoleta(null, 1L, 2L, 3, 500.0, 1500.0);
        DetalleBoleta saved = repository.save(detalle);

        Optional<DetalleBoleta> encontrado = repository.findById(saved.getIdDetalleBoleta());

        assertTrue(encontrado.isPresent());
        assertEquals(3, encontrado.get().getCantidad());
    }

    @Test
    @DisplayName("Buscar todos los detalles por ID de boleta")
    void findAllByIdBoleta() {
        DetalleBoleta d1 = new DetalleBoleta(null, 10L, 1L, 2, 100.0, 200.0);
        DetalleBoleta d2 = new DetalleBoleta(null, 10L, 2L, 1, 300.0, 300.0);
        DetalleBoleta d3 = new DetalleBoleta(null, 99L, 3L, 1, 100.0, 100.0);

        repository.saveAll(List.of(d1, d2, d3));

        List<DetalleBoleta> resultados = repository.findAllByIdBoleta(10L);

        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().allMatch(d -> d.getIdBoleta().equals(10L)));
    }

    @Test
    @DisplayName("Eliminar por ID")
    void deleteById() {
        DetalleBoleta detalle = new DetalleBoleta(null, 5L, 8L, 2, 50.0, 100.0);
        DetalleBoleta saved = repository.save(detalle);

        repository.deleteById(saved.getIdDetalleBoleta());

        Optional<DetalleBoleta> eliminado = repository.findById(saved.getIdDetalleBoleta());
        assertTrue(eliminado.isEmpty());
    }
}