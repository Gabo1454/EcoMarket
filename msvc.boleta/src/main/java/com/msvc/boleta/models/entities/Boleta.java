package com.msvc.boleta.models.entities;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;

@Entity
@Table(name = "boletas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta") // anotacion para JPA
    private Long idBoleta;

    @Column(nullable = false, name = "descripcion_boleta")
    private String descripcionBoleta;

    @Column(nullable = false, name = "id_cliente_pojo")
    private Long idClientePojo;

    @NotNull(message = "Debe incluir la fecha de la boleta")
    private LocalDate fechaBoleta;

    @NotNull(message = "Debe calcularse el total")
    private Double total;
}
