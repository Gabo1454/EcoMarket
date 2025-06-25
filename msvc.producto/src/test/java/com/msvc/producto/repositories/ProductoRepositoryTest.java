package com.msvc.producto.repositories;

import com.msvc.producto.models.entities.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    public void testSaveAndFindById() {
        Producto producto = new Producto(null, "Producto1", 100.0, "Descripción", 10);
        producto = productoRepository.save(producto);

        Optional<Producto> found = productoRepository.findById(producto.getIdProducto());
        assertTrue(found.isPresent());
        assertEquals("Producto1", found.get().getNombre());
    }
}