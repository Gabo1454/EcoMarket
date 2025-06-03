package com.msvc.inventario.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inventarios")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @NotNull(message = "El id producto es obligatorio")
    @Column(nullable = false)
    private Long idProducto;

    @NotNull(message = "El stock no puede estar vacio")
    @Min(value= 0, message = "La cantidad no puede ser negativa")
    @Column(nullable = false)
    private int stock;

    @NotNull(message = "La fecha de inventario es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaInventario;
}
