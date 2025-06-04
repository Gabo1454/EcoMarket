package com.msvc.detalleBoleta.dto;

import lombok.Data;

@Data
public class DetalleBoletaDTO {

    private Long idDetalleBoleta;
    private Long idBoleta;
    private Long idProducto;
    private Integer cantidad;
    private Double precioUnidad;
    private Double subTotal;

}
