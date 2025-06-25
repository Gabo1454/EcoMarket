package com.msvc.inventario.models;

import com.msvc.inventario.models.entities.Inventario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void inventarioValido_noDeberiaTenerViolaciones() {
        Inventario inventario = new Inventario(
                1L,
                100L,
                50,
                LocalDate.now()
        );

        Set<ConstraintViolation<Inventario>> violaciones = validator.validate(inventario);
        assertTrue(violaciones.isEmpty());
    }

    @Test
    void inventarioConStockNegativo_deberiaTenerViolacion() {
        Inventario inventario = new Inventario(
                1L,
                100L,
                -5,
                LocalDate.now()
        );

        Set<ConstraintViolation<Inventario>> violaciones = validator.validate(inventario);
        assertFalse(violaciones.isEmpty());
    }

    @Test
    void inventarioSinFecha_deberiaTenerViolacion() {
        Inventario inventario = new Inventario(
                1L,
                100L,
                10,
                null
        );

        Set<ConstraintViolation<Inventario>> violaciones = validator.validate(inventario);
        assertFalse(violaciones.isEmpty());
    }
}