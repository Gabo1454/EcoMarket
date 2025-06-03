package com.msvc.boleta.models.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;

@Entity
@Table(name = "boletas")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor

public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoleta;

    @NotNull(message = "Debe incluir id Cliente")
    private Long idCliente;

    @NotNull(message = "Debe incluir la fecha de la boleta")
    private LocalDate fechaBoleta;

    @NotNull(message = "Debe calcularse el total")
    private Double total;

}
