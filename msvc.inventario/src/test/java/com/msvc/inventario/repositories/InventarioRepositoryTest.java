package com.msvc.inventario.repositories;

import com.msvc.inventario.models.entities.Inventario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class InventarioRepositoryTest {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Test
    void guardarYBuscarInventario_porId() {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(100L);
        inventario.setStock(20);
        inventario.setFechaInventario(LocalDate.now());

        Inventario guardado = inventarioRepository.save(inventario);

        Optional<Inventario> encontrado = inventarioRepository.findById(guardado.getIdInventario());
        assertTrue(encontrado.isPresent());
        assertEquals(20, encontrado.get().getStock());
    }

    @Test
    void eliminarInventario_deberiaEliminarCorrectamente() {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(101L);
        inventario.setStock(10);
        inventario.setFechaInventario(LocalDate.now());

        Inventario guardado = inventarioRepository.save(inventario);
        Long id = guardado.getIdInventario();

        inventarioRepository.deleteById(id);

        Optional<Inventario> eliminado = inventarioRepository.findById(id);
        assertFalse(eliminado.isPresent());
    }
}