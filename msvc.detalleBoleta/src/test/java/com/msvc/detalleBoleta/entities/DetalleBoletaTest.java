package com.msvc.detalleBoleta.entities;

import com.msvc.detalleBoleta.models.entities.DetalleBoleta;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DetalleBoletaTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testDetalleBoletaValida() {
        DetalleBoleta detalle = new DetalleBoleta(
                1L,
                2L,
                3L,
                5,
                1500.0,
                7500.0
        );

        Set<ConstraintViolation<DetalleBoleta>> violations = validator.validate(detalle);
        assertTrue(violations.isEmpty(), "Debe pasar todas las validaciones");
    }

    @Test
    void testDetalleBoletaInvalida() {
        DetalleBoleta detalle = new DetalleBoleta();
        detalle.setCantidad(0); // Inválido: debe ser mínimo 1
        detalle.setPrecioUnidad(0.0); // Inválido
        detalle.setSubTotal(-1.0); // Inválido

        Set<ConstraintViolation<DetalleBoleta>> violations = validator.validate(detalle);
        assertFalse(violations.isEmpty(), "Debe fallar por múltiples violaciones");

        violations.forEach(v ->
                System.out.println("Violación: " + v.getPropertyPath() + " - " + v.getMessage())
        );
    }
}