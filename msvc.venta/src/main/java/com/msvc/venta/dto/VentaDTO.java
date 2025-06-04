package com.msvc.venta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

    private Long idVenta;
    private LocalDate fechaVenta;
    private Double total;
    private Long idCliente;
    private Long idBoleta;

}
