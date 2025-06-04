package com.msvc.boleta.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class BoletaDTO {

    private Long id;
    private LocalDate fechaEmision;
    private Double montoTotal;
    private Long idVenta;

}


