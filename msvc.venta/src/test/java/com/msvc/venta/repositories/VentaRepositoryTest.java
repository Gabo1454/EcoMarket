package com.msvc.venta.repositories;

import com.msvc.venta.models.entities.Venta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Test
    public void testSaveAndFindById() {
        Venta venta = new Venta(null, LocalDate.now(), 150.0, 1L, 1L);
        venta = ventaRepository.save(venta);

        Optional<Venta> found = ventaRepository.findById(venta.getIdVenta());
        assertTrue(found.isPresent());
        assertEquals(150.0, found.get().getTotal());
    }
}
