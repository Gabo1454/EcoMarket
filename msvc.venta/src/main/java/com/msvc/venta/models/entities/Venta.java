package com.msvc.venta.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @NotNull(message = "La fecha de la venta es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaVenta = LocalDate.now();

    @NotNull(message = "El total de la venta es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor a 0")
    @Column(nullable = false)
    private Double total;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(nullable = false)
    private Long idCliente;

    @NotNull(message = "El ID de la boleta es obligatorio")
    @Column(nullable = false)
    private Long idBoleta;
}